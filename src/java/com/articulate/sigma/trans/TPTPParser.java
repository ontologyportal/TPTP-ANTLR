package com.articulate.sigma.trans;

import java.io.File;
import java.io.Reader;
import java.util.*;
import tptp_parser.*;

public class TPTPParser {

    HashMap<String,TPTPFormula> result = new HashMap<>();

    /** ***************************************************************
     * @param f
     */
    public TPTPParser (File f) throws Exception {

        TPTPVisitor sv = new TPTPVisitor();
        sv.parseFile(f.getAbsolutePath());
        HashMap<String,TPTPFormula> hm = TPTPVisitor.result;
    }

    /** ***************************************************************
     */
    public TPTPParser (String contents) throws Exception {

        result = TPTPVisitor.parseString(contents);
    }

    /** ***************************************************************
     * Convenience routine that calls the main convert() method below
     */
    public static TPTPParser parse(String s) throws Exception {
            return new TPTPParser(s);
    }
}
