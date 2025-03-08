# About
This repository contains an ANTLR4 grammar for the logic input languages provided by the TPTP [1].
This was originally created by Alexander Steen (a.steen@fu-berlin.de) and Tobias Gleißner (tobias.gleissner@fu-berlin.de)
and distributed at https://github.com/TobiasGleissner/TPTP-ANTLR4-Grammar as of 2018
The grammar was successfully tested on all valid syntactical problems of the TPTP.

Adam Pease (apease@articulatesoftware.com) has added a TPTPVisitor class and TPTPFormula
structure to hold the results of parsing.

Note that TPTP has continued to evolve and as of 2021 this grammar is behind the
latest version, although it appears that TFF, FOF and CNF formulas haven't changed.

# Contents
* tptp_v7_0_0_0.g4

    The actual grammar.

* test

    Contains code to test the grammar on a set of TPTP problems.

* test_cases

    Contains additional test cases for (unofficial) non-classical logics.

[1] http://www.tptp.org/

## To build/run/debug/test using the NetBeans IDE
Define a nbproject/private/private.properties file with these keys:

\# private properties\
javaapis.dir=${user.home}/javaapis\
workspace=${javaapis.dir}/INSAFE

\# The default installation space is: ~/workspace. However, it can be anywhere on\
\# your system as long as you define the "workspace" key above.

\# JavaMail properties\
user=${your.email.user.name}\
my.email=${user}@${your.email.domain}\
my.name=${your.name}
