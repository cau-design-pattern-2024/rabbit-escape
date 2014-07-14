package rabbitescape.engine.util;

import static rabbitescape.engine.util.Util.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class FakeFileSystem implements FileSystem
{
    private final Map<String, String[]> files;

    public FakeFileSystem( Object... namesAndContents )
    {
        reAssert( namesAndContents.length % 2 == 0 ); // Even number of args

        files = new TreeMap<String, String[]>();

        for ( int i = 0; i < namesAndContents.length; i += 2 )
        {
            String name = (String)namesAndContents[i];
            String[] contents = (String[])namesAndContents[ i + 1 ];
            files.put( name, contents );
        }
    }

    @Override
    public boolean exists( String fileName )
    {
        return files.containsKey( fileName );
    }

    @Override
    public String[] readLines( String fileName )
        throws FileNotFoundException, IOException
    {
        if ( exists( fileName ) )
        {
            return files.get( fileName );
        }
        else
        {
            throw new FileNotFoundException(
                "File '" + fileName + " not found." );
        }
    }

}
