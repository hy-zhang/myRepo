# Design Custom Transformations with Lombok

Sometimes you need to work on the project [Lombok](https://projectlombok.org/), and extend it (create your own annotations and handlers).

There is a [blog](http://notatube.blogspot.hk/2010/12/project-lombok-creating-custom.html) which provides detailed instructions. However, it seems to be out-of-date since the framework structure of Lombok has been slightly changed. The current version is v1.16.4.

## Steps

- Clone the github repo of Lombok. Click [here](https://github.com/rzwitserloot/lombok).
- Run a terminal. Use the command "__ant eclipse__" at the root directory and the dependencies will be downloaded automatically. 
- Then use "__ant__" to compile the project and  generate .jar file. There'll be two .jar files under the folder "dist/". They are the same.
- To use the annotations defined in Lombok, you need to run the .jar file first (just double click it), so that Lombok is installed in Eclipse. Also when you use an annotation, you should also include this .jar file in the build path.
- When you define your own annotation in the project, you should also compile it first (run "__ant__"), get the .jar file, run it, and include it in where the annotation is used.

## How to define a new annotation

The directory "src/core/lombok/" contains the source code. Let's take "__@Data__" as an example.

- Firstly declare an annotation interface as "Data.java" does.
- In "eclipse/handlers" you need to implement a handler for Eclipse. See "HandleData.java".
- Also in "javac/handlers" implement a handler for Javac. See "HandleData.java".

Done. After that just compile the project and use the annotation.

