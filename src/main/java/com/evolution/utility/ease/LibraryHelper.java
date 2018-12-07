package com.evolution.utility.ease;

import com.evolution.reference.Index;
import com.evolution.utility.LogHelper;
import com.google.gson.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LibraryHelper
{

    private static File librariesDirectory = Index.Files.LIBRARIES_DIRECTORY;
    private static File librariesJsonIndex = new File(librariesDirectory, "modLibraries.json");
    private static Map<String, Library> libraries = new HashMap<>();

    public static void registerLibraries()
    {
        if(!librariesDirectory.exists()){ librariesDirectory.mkdirs(); }

        readLibrariesFromJsonFile();
    }

    private static void registerLibrary(String libraryName, File libraryFile)
    {
        if(!isLibraryRegistered(libraryFile.getName()))
        {
            try
            {
                Library library = new Library(libraryFile.getName(), libraryFile);
                libraries.put(libraryName, library);
//                LogHelper.writeToConsoleAndFile(String.format("Successfully registered Library: \'%s\'.", libraryFile.getName()));
            }
            catch(Exception ex)
            {
                throw new RuntimeException(ex);
            }
        }
        else
        {
            throw new RuntimeException(String.format("Failed to register Library: \'%s\', because the library is already registered.", libraryFile.getPath()));
        }
    }

    public static Library getLibrary(String libraryName)
    {
        for(Map.Entry<String, Library> lib : libraries.entrySet())
        {
            if(lib.getKey().contains(libraryName)){ return lib.getValue(); }
        }
        throw new RuntimeException(String.format("Library: \'%s\' could not be found. Make sure the library name is correct, and that the library has been registered.", libraryName));
    }

    public static boolean isLibraryRegistered(String libraryName)
    {
        for(Map.Entry<String, Library> l : libraries.entrySet())
        {
            if(l.getKey().contains(libraryName) || l.getValue().getLibraryName().contains(libraryName)){ return validateLibrary(l.getValue()); }
        }
        return false;
    }

    public static Map<String, Library> getLibraries(){ return libraries; }

    private static boolean validateLibrary(Library l)
    {
        boolean hasClasses = false;
        boolean hasFields = false;
        boolean hasMethods = false;
        if(l.getClasses().size() > 0){ hasClasses = true; }
        if(l.getClassToFieldsMap() != null && l.getClassToFieldsMap().size() > 0){ hasFields = true; }
        if(l.getClassToMethodsMap() != null && l.getClassToMethodsMap().size() > 0){ hasMethods = true; }
        return hasClasses || hasFields || hasMethods;
    }

    private static void readLibrariesFromJsonFile()
    {
        if(!librariesJsonIndex.exists()){ FileHelper.verifyFileExists(librariesJsonIndex, true); }
        if(FileHelper.isFileEmpty(librariesJsonIndex)){ writeLibrariesIndexFile(); }
        JsonObject librariesIndex = JsonHelper.getJsonFromFile(librariesJsonIndex);
        String url_template = librariesIndex.get("url_template").getAsString();
        JsonObject libraries = librariesIndex.get("libraries").getAsJsonObject();
        for(Map.Entry<String, JsonElement> library : libraries.entrySet())
        {
            String libInternalName = library.getKey();
            JsonObject vals = library.getValue().getAsJsonObject();
            String url_path = vals.get("url_path").getAsString();
            String file = vals.get("file").getAsString();

            URL library_url = null;
            try{ library_url = new URL(url_template + url_path); }catch(Exception ex){ ex.printStackTrace(); }
            File library_file = new File(Index.Files.LIBRARIES_DIRECTORY, file);

            if(!library_file.exists())
            {
                FileHelper.verifyFileExists(library_file, true);
                try{ FileUtils.copyURLToFile(library_url, library_file); }
                catch(IOException ex){ ex.printStackTrace(); }
            }

            if(!FileHelper.isFileEmpty(library_file))
            {
                if(!isLibraryRegistered(libInternalName))
                {
                    registerLibrary(libInternalName, library_file);
                }
            }
        }
    }

    private static void writeLibrariesIndexFile()
    {
        try
        {
            FileUtils.copyURLToFile(new URL("https://raw.githubusercontent.com/randysdoom/Evolution/master/modLibraries.json"), librariesJsonIndex);
        }catch(Exception ex){ ex.printStackTrace(); }
    }

    public static class Library
    {
        private boolean isValid;
        private String libraryName;
        private File libraryFile;
        private URLClassLoader libraryClassLoader;
        private Map<String, Class> classes;
        private Map<Class, Map<String, Field>> classToFieldsMap;
        private Map<Class, Map<String, Method>> classToMethodsMap;

        private static final String classException = "Class: \'%s\', could not be found. Make sure the class name is valid, and that the library that contains that class is loaded.";
        private static final String methodException = "Method: \'%s\', could not be found. Make sure the method name is valid, and that the class that contains the method is loaded.";
        private static final String fieldException = "Field: \'%s\', could not be found. Make sure the field name is valid, and that the class that contains the field is loaded.";

        Library(String libraryName, File libraryFile)
        {
            this.libraryName = libraryName;
            this.libraryFile = libraryFile;
            this.initLibrary();
        }

        private void initLibrary()
        {
            this.classes = new HashMap<>();
            this.classToFieldsMap = new HashMap<>();
            this.classToMethodsMap = new HashMap<>();
            try
            {
                JarFile jar = new JarFile(libraryFile);
                Enumeration<JarEntry> e = jar.entries();

                URL[] urls = new URL[]{ new URL("jar:file:" + libraryFile.getPath() + "!/") };
                libraryClassLoader = URLClassLoader.newInstance(urls);

                while(e.hasMoreElements())
                {
                    JarEntry je = e.nextElement();
                    if(je.isDirectory() || !je.getName().endsWith(".class")){ continue; }
                    String className = je.getName().substring(0, je.getName().length() - 6);
                    Class c = libraryClassLoader.loadClass(className.replace("/", "."));
                    classes.put(className, c);

                    if(c.getDeclaredFields().length > 0)
                    {
                        Map<String, Field> fields = new HashMap<>();
                        for(Field f : c.getDeclaredFields())
                        {
                            fields.put(f.getName(), f);
                        }
                        classToFieldsMap.put(c, fields);
                    }

                    if(c.getDeclaredMethods().length > 0)
                    {
                        Map<String, Method> methods = new HashMap<>();
                        for(Method m : c.getDeclaredMethods())
                        {
                            methods.put(m.getName(), m);
                        }
                        classToMethodsMap.put(c, methods);
                    }
                }
                jar.close();
            }
            catch(Exception ex){ ex.printStackTrace(); }
        }

        private Class getClass(String className)
        {
            Class c = null;
            try
            {
                c = Class.forName(className, true, this.libraryClassLoader);
            }
            catch(ClassNotFoundException ex)
            {
                throw new RuntimeException(String.format(classException, className));
            }
            return c;
        }

        private Map<String, Field> getFieldsForClass(Class clazz)
        {
            for(Map.Entry<String, Class> entry : this.classes.entrySet())
            {
                if(entry.getValue().equals(clazz))
                {
                    Map<String, Field> fields = new HashMap<>();
                    for(Field f : entry.getValue().getDeclaredFields())
                    {
                        fields.put(f.getName(), f);
                    }
                    if(fields.size() > 0){ return fields; }
                }
            }
            throw new RuntimeException(String.format(classException, clazz.getCanonicalName()));
        }

        private Map<String, Method> getMethodsForClass(Class clazz)
        {
            for(Map.Entry<String, Class> entry : this.classes.entrySet())
            {
                if(entry.getValue().equals(clazz))
                {
                    Map<String, Method> methods = new HashMap<>();
                    for(Method m : entry.getValue().getDeclaredMethods())
                    {
                        methods.put(m.getName(), m);
                    }
                    if(methods.size() > 0){ return methods; }
                }
            }
            throw new RuntimeException(String.format(classException, clazz.getCanonicalName()));
        }

        public Field getField(String className, String fieldName)
        {
            Class c = this.getClass(className);
            if(c != null)
            {
                Map<String, Field> fields = getFieldsForClass(c);
                if(fields != null)
                {
                    for(Map.Entry<String, Field> field : fields.entrySet())
                    {
                        if(field.getKey().equals(fieldName))
                        {
                            return field.getValue();
                        }
                    }
                }
                throw new RuntimeException(String.format(fieldException, fieldName));
            }
            throw new RuntimeException(String.format(classException, className));
        }

        public Method getMethod(String className, String methodName)
        {
            Class c = this.getClass(className);
            if(c != null)
            {
                Map<String, Method> methods = getMethodsForClass(c);
                if(methods != null)
                {
                    for(Map.Entry<String, Method> method : methods.entrySet())
                    {
                        if(method.getKey().equals(methodName))
                        {
                            return method.getValue();
                        }
                    }
                }
                throw new RuntimeException(String.format(methodException, methodName));
            }
            throw new RuntimeException(String.format(classException, className));
        }

        public Object getFieldValue(Field f, Object returnType)
        {
            try{ return f.get(returnType); }
            catch(Exception ex){ ex.printStackTrace(); }
            return returnType;
        }

        public Object getFieldValue(String className, String fieldName, Object returnType)
        {
            return this.getFieldValue(this.getField(className, fieldName), returnType);
        }

        public <T> T invokeMethod(String className, String methodName, Object calledFrom, T returnType, Object... args)
        {
            return this.invokeMethod(this.getMethod(className, methodName), calledFrom, returnType, args);
        }

        public <T> T invokeMethod(Method m, Object calledFrom, T returnType, Object... args)
        {
            try
            {
                return (T) m.invoke(calledFrom, args);
            }catch(Exception ex){ ex.printStackTrace(); }
            throw new RuntimeException(String.format("Failed to invoke Method: %s, ensure that the return type is correct, and make sure that you are not providing args to a method that doesn't have any parameters.", m.getName()));
        }

        public File getLibraryFile(){ return this.libraryFile; }

        public URLClassLoader getLibraryClassLoader(){ return this.libraryClassLoader; }

        public void printLibraryToLogFile()
        {
            JsonObject library = new JsonObject();
            library.add("name", new JsonPrimitive(this.getLibraryName()));
            JsonObject classes = new JsonObject();

            for(Map.Entry<String, Class> ces : this.getClasses().entrySet())
            {
                JsonObject clazz = new JsonObject();

                if(ces.getValue().getDeclaredFields().length > 0)
                {
                    JsonObject fields = new JsonObject();
                    for(Map.Entry<String, Field> fes : this.getFieldsForClass(ces.getValue()).entrySet())
                    {
                        Field f = fes.getValue();
                        JsonObject field = new JsonObject();
                        field.add("return_type", new JsonPrimitive(f.getType().getName()));
                        fields.add(fes.getKey(), field);
                    }
                    if(fields.entrySet().size() > 0){ clazz.add("fields", fields); }
                }

                if(ces.getValue().getDeclaredMethods().length > 0)
                {
                    JsonObject methods = new JsonObject();
                    for(Map.Entry<String, Method> mes : this.getMethodsForClass(ces.getValue()).entrySet())
                    {
                        Method m = mes.getValue();
                        JsonObject method = new JsonObject();
                        method.add("return_type", new JsonPrimitive(m.getReturnType().getName()));
                        if(m.getParameterCount() > 0)
                        {
                            JsonObject parameters = new JsonObject();
                            for(Parameter p : m.getParameters())
                            {
                                JsonObject parameter = new JsonObject();
                                parameter.add("type", new JsonPrimitive(p.getType().getName()));
                                parameters.add(p.getName(), parameter);
                            }
                            if(parameters.entrySet().size() > 0){ method.add("parameters", parameters); }
                        }
                        methods.add(mes.getKey(), method);
                    }
                    if(methods.entrySet().size() > 0){ clazz.add("methods", methods); }
                }

                classes.add(ces.getValue().getName(), clazz);
            }
            library.add("classes", classes);

            String s = JsonHelper.format(library, true);
            File f = new File("logs/mods/Evolution/Testing/LibraryToJson.json");
            FileHelper.verifyFileExists(f, true);
            FileHelper.Write.writeToFile(f, s);
        }

        public String getLibraryName(){ return this.libraryName; }

        public Map<String, Class> getClasses(){ return this.classes; }

        public Map<Class, Map<String, Field>> getClassToFieldsMap(){ return this.classToFieldsMap; }

        public Map<Class, Map<String, Method>> getClassToMethodsMap(){ return this.classToMethodsMap; }

    }

}
