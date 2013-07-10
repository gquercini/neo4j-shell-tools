package org.neo4j.shell.tools.imp.util;

import java.io.*;

/**
* Created by mh on 10.07.13.
*/
public class CountingReader extends FilterReader implements SizeCounter {
    public static final int BUFFER_SIZE = 1024 * 1024;
    private final long total;
    long count=0;

    public CountingReader(File file) throws FileNotFoundException {
        super(new BufferedReader(new FileReader(file), BUFFER_SIZE));
        this.total = file.length();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int read = super.read(cbuf, off, len);
        count+=read;
        return read;
    }

    public long getCount() {
        return count;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public long getPercent() {
        return getCount()*100 / getTotal();
    }
}
