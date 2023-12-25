package de.tum.cit.ase.maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    /**
     * Reading the file and mapping the data into key and values and returning it.
     * @param filePath String containing the filePath of the file to be read.
     * @return Map with a list of two integers [x, y] as key and an integer (value) as value
     */
    public static Map<List<Integer>, Integer> readMap (String filePath)
    {
        Map<List<Integer>, Integer> map = new HashMap<>();
        try{
            Path path = Paths.get(filePath);
            List<String> lines  = Files.readAllLines(path);
            for (String line : lines)
            {
                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String[] xy = parts[0].split(",");
                    int x = Integer.parseInt(xy[0].trim());
                    int y = Integer.parseInt(xy[1].trim());
                    int value = Integer.parseInt(parts[1].trim());
                    List<Integer> coordinates = new ArrayList<>();
                    coordinates.add(x);
                    coordinates.add(y);
                    map.put(coordinates, value);
                }else {
                    System.err.println("Invalid Map");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return (map);
    }
}
