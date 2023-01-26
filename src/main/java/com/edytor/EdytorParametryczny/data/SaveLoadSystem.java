package com.edytor.EdytorParametryczny.data;
import org.jgraph.graph.DefaultEdge;
import org.json.JSONObject;

import com.edytor.EdytorParametryczny.components.DrawAndGate;
import com.edytor.EdytorParametryczny.components.DrawComponent;
import com.edytor.EdytorParametryczny.components.DrawDiamond;
import com.edytor.EdytorParametryczny.components.DrawElipse;
import com.edytor.EdytorParametryczny.components.DrawPolygon;
import com.edytor.EdytorParametryczny.components.DrawRoundRect;
import com.edytor.EdytorParametryczny.components.DrawSquare;
import com.edytor.EdytorParametryczny.components.DrawTriangle;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class SaveLoadSystem {
	
	
	public static void Load(String filePath)
	{
		try {
			FileInputStream fis = new FileInputStream(filePath);
			String jsonString = readFromInputStream(fis);
			
			JSONObject obj = new JSONObject(jsonString);
			LoadCells(obj);
			LoadEdges(obj);
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	public static void Save(String filePath)
	{
		JSONObject obj = new JSONObject();
		SaveCells(obj);
		SaveEdges(obj);
		
		try {
			@SuppressWarnings("resource")
			FileWriter writer = new FileWriter(filePath);
			writer.write(obj.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void SaveCells(JSONObject obj)
	{	
		List<DrawComponent> list = EdytorData.GetCells();
		
		for(int i=0;i<list.size();i++)
		{
			JSONObject cellInfo = new JSONObject();
			
			DrawComponent cell = list.get(i);
			
			cellInfo.put("X",cell.GetX());
			cellInfo.put("Y",cell.GetY());
			cellInfo.put("Width",cell.getSize().width);
			cellInfo.put("Height",cell.getSize().height);
			JSONObject bg = new JSONObject();
			bg.put("r",cell.getBackroundColor().getRed());
			bg.put("g",cell.getBackroundColor().getGreen());
			bg.put("b",cell.getBackroundColor().getBlue());
			cellInfo.put("Backround_Color",bg);
			JSONObject fg = new JSONObject();
			fg.put("r",cell.getForegroundColor().getRed());
			fg.put("g",cell.getForegroundColor().getGreen());
			fg.put("b",cell.getForegroundColor().getBlue());
			cellInfo.put("Foreground_Color",fg);
			String text = cell.GetText();
			cellInfo.put("Text",cell.GetText());
			cellInfo.put("Shape",cell.GetName());
			if(cell instanceof DrawTriangle)
			{
				DrawTriangle tri = (DrawTriangle) cell;
				cellInfo.put("Tri_angle",tri.GetTrinagleFloat());
			}
			if(cell instanceof DrawPolygon)
			{
				DrawPolygon tri = (DrawPolygon) cell;
				cellInfo.put("N_size",tri.getNSize());
			}
			
			String name = "Shape"+i;
			
			obj.put(name,cellInfo);
		}
	}
	
	private static void SaveEdges(JSONObject obj)
	{
		List<DrawComponent> cellList = EdytorData.GetCells();
		List<DefaultEdge> edgesList = EdytorData.GetEdges();
		
		for(int i=0;i<edgesList.size();i++)
		{
			DefaultEdge edge = edgesList.get(i);
			
			String source_name = "None";
			String target_name = "None";
			
			if(edge.getSource()!=null)
			{
				for(int j=0;j<cellList.size();j++)
				{
					if(cellList.get(j).GetCell().getChildAt(0).equals(edge.getSource()))
					{
						source_name = "Shape"+j;
					}
				}
			}
			if(edge.getTarget()!=null)
			{
				for(int j=0;j<cellList.size();j++)
				{
					if(cellList.get(j).GetCell().getChildAt(0).equals(edge.getTarget()))
					{
						target_name = "Shape"+j;
					}
				}
			}
			
			JSONObject edgeInfo = new JSONObject();
			edgeInfo.put("Source", source_name);
			edgeInfo.put("Target", target_name);
			edgeInfo.put("ArrowType", edge.getAttributes().get("lineEnd"));
			edgeInfo.put("Text", edge.getUserObject().toString());
			
			String name = "Edge"+i;
			obj.put(name,edgeInfo);
		}
		
	}
	
	private static String readFromInputStream(InputStream inputStream)
			  throws IOException {
			    StringBuilder resultStringBuilder = new StringBuilder();
			    try (BufferedReader br
			      = new BufferedReader(new InputStreamReader(inputStream))) {
			        String line;
			        while ((line = br.readLine()) != null) {
			            resultStringBuilder.append(line).append("\n");
			        }
			    }
			  return resultStringBuilder.toString();
			}
	
	
	
	private static void LoadCells(JSONObject obj)
	{
			for(Map.Entry<String, Object> o : obj.toMap().entrySet())
			{
				
				if(!o.getKey().startsWith("Shape"))
					continue;
				
				JSONObject cell = (JSONObject) obj.get(o.getKey());
				
				
				
				int x = cell.getInt("X");
				int y = cell.getInt("Y");
				int width = cell.getInt("Width");
				int height = cell.getInt("Height");
				JSONObject bgJson = (JSONObject) cell.get("Backround_Color");
				Color bg = new Color(bgJson.getInt("r"),bgJson.getInt("g"),bgJson.getInt("b"));
				JSONObject fgJson = (JSONObject) cell.get("Foreground_Color");
				Color fg = new Color(fgJson.getInt("r"),fgJson.getInt("g"),fgJson.getInt("b"));
				
				String text = "";
				if(cell.has("Text"))
				{
					text =  cell.getString("Text");
				}
				
				String cellShape = (String) cell.get("Shape");
				switch(cellShape)
				{
				
				case "Diamond":
					DrawDiamond diamond = new DrawDiamond(width,height);
					diamond.SetCords(x, y);
					diamond.SetBackgroundColor(bg);
					diamond.SetForegroundColor(fg);
					diamond.SetText(text);
					EdytorData.AddCell(diamond);
					break;
					
				case "AndGate":
					DrawAndGate andGate = new DrawAndGate(width,height);
					andGate.SetCords(x, y);
					andGate.SetBackgroundColor(bg);
					andGate.SetForegroundColor(fg);
					andGate.SetText(text);
					EdytorData.AddCell(andGate);
					break;
					
				case "Elipse":
					DrawElipse elipse = new DrawElipse(width,height);
					elipse.SetCords(x, y);
					elipse.SetBackgroundColor(bg);
					elipse.SetForegroundColor(fg);
					elipse.SetText(text);
					EdytorData.AddCell(elipse);
					break;
					
				case "Polygon":
					int n = cell.getInt("N_size");
					
					DrawPolygon polygon = new DrawPolygon(width,height);
					polygon.SetCords(x, y);
					polygon.SetBackgroundColor(bg);
					polygon.SetForegroundColor(fg);
					polygon.SetText(text);
					polygon.setNSize(n);
					EdytorData.AddCell(polygon);
					break;
					
				case "RoundRect":
					DrawRoundRect roundRect = new DrawRoundRect(width,height);
					roundRect.SetCords(x, y);
					roundRect.SetBackgroundColor(bg);
					roundRect.SetForegroundColor(fg);
					roundRect.SetText(text);
					EdytorData.AddCell(roundRect);
					break;
					
				case "Triangle":
					float tri = cell.getFloat("Tri_angle");
					
					DrawTriangle triangle = new DrawTriangle(width,height);
					triangle.SetCords(x, y);
					triangle.SetBackgroundColor(bg);
					triangle.SetForegroundColor(fg);
					triangle.SetText(text);
					triangle.SetTriangleFloat(tri);
					EdytorData.AddCell(triangle);
					break;
				
				case "default":
				case "Square":
					DrawSquare square = new DrawSquare(width,height);
					square.SetCords(x, y);
					square.SetBackgroundColor(bg);
					square.SetForegroundColor(fg);
					square.SetText(text);
					EdytorData.AddCell(square);
					break;
				}

			}
	}
	
	private static void LoadEdges(JSONObject obj)
	{
		for(Map.Entry<String, Object> o : obj.toMap().entrySet())
		{
			if(!o.getKey().startsWith("Edge"))
				continue;
			
			JSONObject edge = (JSONObject) obj.get(o.getKey());
			
			String source_name = edge.getString("Source");
			String target_name = edge.getString("Target");
			int arrowType = edge.getInt("ArrowType");
			String text = "";
			if(edge.has("Text"))
			{
				text =  edge.getString("Text");
			}
			
			int source = -1;
			int target = -1;
			
			if(!source_name.contentEquals("None"))
			{
				int tmp1 = -1;
				String tmpS1 = source_name.replaceAll("Shape", "");
				tmp1 = Integer.parseInt(tmpS1);
				if(tmp1>-1)
				{
					source = tmp1;
				}
			}
			
			if(!target_name.contentEquals("None"))
			{
				int tmp2 = -1;
				String tmpS2 = target_name.replaceAll("Shape", "");
				tmp2 = Integer.parseInt(tmpS2);
				if(tmp2>-1)
				{
					target = tmp2;
				}
			}
			
			EdytorData.AddEdge(text, arrowType, source, target);
			
		}
	}
}
