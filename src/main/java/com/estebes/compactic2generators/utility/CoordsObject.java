package com.estebes.compactic2generators.utility;

import java.util.Queue;

public class CoordsObject
{
    // Vars
    private int x, y, z;

    public CoordsObject(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void rotateCoord(Queue<CoordsObject> coordsQueue)
    {
        coordsQueue.add(coordsQueue.poll());
    }
}
