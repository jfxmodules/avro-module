# jfxmodules Avro

The code for this project is taken from the [Apache Avro](https://github.com/apache/avro)
project. The current build is taken from the `release-1.11.3` branch.

There is no relationship between this project and the offical Apache Avro project. This project
is just the [Avro Java lang code](https://github.com/apache/avro/tree/main/lang/java/avro). The main
goal of this project is to turn the Apache Avro jar file into a proper
Java module. 

To see all the changes made to this project please see the diff.out file found
in the root of this project.

## Changes:
Here is a summary of changes made to this code base.

- Heavily changed the POM file to build just this part of the project.
- Got rid of OSGI from POM.
- Copied the shared schemas over from the parent project.
- This library requires JDK 21 or above.
- Removed the following libraries in favor of commons-compress:
    - org.lz4
    - com.github.luben
    - org.xerial.snappy
- Modified SnappyCodec.java to use commons-compress.
- Removed following compression libraries because they use dependencies that are 
not proper java modules.
    - ZstandardCodec
    - XZCodec

License: Apache License Version 2.0




