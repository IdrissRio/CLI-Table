# CLI-Table [![](https://jitpack.io/v/idrissrio/cli-table.svg)](https://jitpack.io/#idrissrio/cli-table)
Java library to create a Table. This library provides methods to query and modify
a table.

A table can be built from STD input or constructed by providing headers and values manually.

# How to use it

To use table you can add to your dependencies:

## Gradle
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        //... Add other dependencies here.
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency:
```
dependencies {
    implementation 'com.github.idrissrio:cli-table:f656a29001'
}
```

## Maven
Add it in your root build.gradle at the end of repositories:
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add the dependency:
```
<dependency>
    <groupId>com.github.idrissrio</groupId>
    <artifactId>cli-table</artifactId>
    <version>f656a29001</version>
</dependency>
```

# Pritning table
The following is an example of how the table is visualised using the method `toString()`.

```
+---------------------+------------+----------+--------------+------------+--------------+--------+----------+
|        NAME         | LINE_START | LINE_END | COLUMN_START | COLUMN_END |   REL_PATH   | VALUE  |   TYPE   |
+---------------------+------------+----------+--------------+------------+--------------+--------+----------+
|     MethodDecl      |     2      |    5     |      10      |     13     | Example.java |  bar   |   void   |
| VariableDeclarator  |     3      |    3     |      13      |     16     | Example.java |  BAR   |   int    |
|        Block        |     2      |    5     |      15      |     5      | Example.java |   -    |    -     |
|      ClassDecl      |     1      |    6     |      1       |     1      | Example.java |   -    | Example  |
| PrimitiveTypeAccess |     3      |    3     |      9       |     11     | Example.java |  int   |   int    |
| PrimitiveTypeAccess |     0      |    0     |      0       |     0      | Example.java |  void  |   void   |
|     VarDeclStmt     |     3      |    3     |      9       |     20     | Example.java |   -    |    -     |
|      Modifier       |     1      |    1     |      1       |     6      | Example.java | public | Modifier |
|   IntegerLiteral    |     3      |    3     |      19      |     19     | Example.java |   2    |   int    |
+---------------------+------------+----------+--------------+------------+--------------+--------+----------+

```