package com.edytor.EdytorParametryczny;


import org.jgrapht.*;
import org.jgrapht.generate.*;
import org.jgrapht.graph.*;
import org.jgrapht.nio.ExportException;
import org.jgrapht.traverse.*;
import org.jgrapht.util.*;

import java.net.URISyntaxException;
import java.util.*;
import java.util.function.*;


public class App 
{
	private static final int SIZE = 10;
	
	
    public static void main( String[] args )
    {
    	/*
    	TestClass test = new TestClass();
    	try {
			test.Run();
		} catch (ExportException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	Test2 test2 = new Test2();
    	test2.Run();
    	
        Test();
    }
    
    private static void Test()
    {
System.out.println( "Hello World!" );
        
        Supplier<String> vSupplier = new Supplier<String>()
        {
            private int id = 0;

            @Override
            public String get()
            {
                return "v" + id++;
            }
        };

        // Create the graph object
        Graph<String, DefaultEdge> completeGraph =
            new SimpleGraph<>(vSupplier, SupplierUtil.createDefaultEdgeSupplier(), false);

        // Create the CompleteGraphGenerator object
        CompleteGraphGenerator<String, DefaultEdge> completeGenerator =
            new CompleteGraphGenerator<>(SIZE);

        // Use the CompleteGraphGenerator object to make completeGraph a
        // complete graph with [size] number of vertices
        completeGenerator.generateGraph(completeGraph);

        // Print out the graph to be sure it's really complete
        Iterator<String> iter = new DepthFirstIterator<>(completeGraph);
        while (iter.hasNext()) {
            String vertex = iter.next();
            System.out
                .println(
                    "Vertex " + vertex + " is connected to: "
                        + completeGraph.edgesOf(vertex).toString());
        }
    }
}
