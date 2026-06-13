public class TestReflection {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("Usuario");
            System.out.println("Class Name: " + clazz.getName());

            //-- Acceder a los metodos declarados (publicos y privados)
            System.out.println("Methods:");
            for (var method : clazz.getDeclaredMethods()) {
                System.out.println(method.getName());
            }

            //-- Acceder a los constructores publicos (declarados)
            System.out.println("Constructors:");
            for (var constructor : clazz.getConstructors()) {
                System.out.println(constructor);
            }

            //-- Acceder a los metodos privados (declarados)
            System.out.println("Private Methods:");
            for (var method : clazz.getDeclaredMethods()) {
                if (method.getModifiers() == java.lang.reflect.Modifier.PRIVATE) {
                    System.out.println(method.getName());
                }
            }

            //-- Reconstruir un objeto usando el constructor
            var constructor = clazz.getConstructor(String.class, int.class);
            var usuario = constructor.newInstance("Juan", 25);
            //-- Invocar un metodo publico
            var saludarMethod = clazz.getMethod("saludar");
            saludarMethod.invoke(usuario);
             //-- Invocar un metodo privado
            var mostrarInformacionMethod = clazz.getDeclaredMethod("mostrarInformacion");
            mostrarInformacionMethod.setAccessible(true);
            mostrarInformacionMethod.invoke(usuario);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}