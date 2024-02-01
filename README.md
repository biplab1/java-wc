# java-wc
A simple Java-based tool that incorporates some of the functionalities of the wc (word count) tool.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [Usage](#usage)
- [Examples](#examples)
- [Reference](#reference)
- [License](#license)

## Introduction

Welcome to **java-wc**, a simple Java-based tool designed to provide essential functionalities akin to the widely-used '**wc**' (word count) tool. This tool provides essential input file analysis capabilities, including line count, word count, character count, and byte count. The development of java-wc was driven by a desire to enhance programming skills and gain hands-on experience.

## Features

- **Word Count**: Easily determine the number of words in a input file.
- **Line Count**: Obtain the total number of lines within a specified file.
- **Character Count**: Calculate the total character count, including spaces, in a given input file.
- **Byte Count**: Measure the total byte count in a input file.

**Note**: The byte count feature includes the ability to override the character count, and vice versa. Whichever comes last in the list of options will take precedence. The output is always in the following order: line count, word count, character/byte count and file name. 

When no files are specified, the program utilizes the standard input, and there is no presentation of a file name. When multiple input files are specified, a line containing cumulative counts for all the files is presented separately following the output for the last file along with the keyword `total`.


## Getting Started

Explore the [Installation](#installation) and [Usage](#usage) sections below.

### Prerequisites

This code requires JDK-8 or later to be installed on your local computer. 

## Installation

```bash

# Clone the repository
git clone https://github.com/biplab1/java-wc

# Navigate to the project directory
cd java-wc

# Compile using the java compiler
javac wc.java
```

## Usage

```bash

# Normal Usage
usage: java wc [-clmw] [file ...]

# Using Piped Input
usage: cat test.txt | java wc [-clmw]

```

## Examples

To demonstrate the usage of **java-wc**, let's consider the `test.txt` and `test1.txt` files from the `examples` folder. Copy these files into the same directory as your compiled code. Before executing the commands below, ensure the mentioned files are in the directory with the compiled code. Alternatively, you can use `examples/test.txt` instead of `test.txt` if running commands from the repository folder.

```bash
# Number of lines
$ java wc -l test.txt
    7145 test.txt

# Number of words
$ java wc -w test.txt
   58164 test.txt

# Number of bytes
$ java wc -c test.txt
  342190 test.txt

# Number of characters
$ java wc -m test.txt
  339292 test.txt

```
```bash

# Default option is equivalent to specifying -l, -w and -c options
$ java wc test.txt
    7145   58164  342190 test.txt

# Piped input with options combined together
$ cat test.txt | wc -lmw
    7145   58164  339292

# Multiple input files
$ java wc -lmw test.txt test1.txt
    7145   58164  339292 test.txt 
    3761   29564  163949 test1.txt 
   10906   87728  503241 total
```

## Reference
This project idea has been taken from [Coding Challenges](https://codingchallenges.fyi/challenges/challenge-wc).


## License

This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for details.


