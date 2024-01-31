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

When no files are specified, the program utilizes the standard input, and there is no presentation of a file name.


## Getting Started

Explore the [Installation](#installation) and [Usage](#usage) sections below.

### Prerequisites

This code requires JDK-8 or later to be installed on your local computer. 

### Installation

```bash

# Clone the repository
git clone https://github.com/biplab1/java-wc

# Navigate to the project directory
cd java-wc

# Compile using the java compiler
javac wc.java
```

### Usage

```bash

# Normal Usage
usage: java wc [-clmw] [file ...]

# Using Piped Input
usage: cat test.txt | java wc [-clmw]

```

### Examples

To demonstrate the usage of **java-wc**, let's consider the `test.txt` file located in the `examples` folder. Make sure you navigate to the examples directory before running the following commands, or you can also use `examples/test.txt` instead of `test.txt` if you are running commands from the repository folder.

```bash
$ java wc -l test.txt
    7145 test.txt

$ java wc -w test.txt
   58164 test.txt

$ java wc -c test.txt
  342190 test.txt

$ java wc -m test.txt
  339292 test.txt

$ java wc test.txt
    7145   58164  342190 test.txt

$ cat test.txt | wc -lmw
    7145   58164  339292
```

## Reference
This project idea has been taken from [Coding Challenges](https://codingchallenges.fyi/challenges/challenge-wc).


## License

This project is licensed under the [MIT License](LICENSE) - see the [LICENSE](LICENSE) file for details.


