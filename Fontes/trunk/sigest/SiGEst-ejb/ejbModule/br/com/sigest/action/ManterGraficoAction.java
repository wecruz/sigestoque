package br.com.sigest.action;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.AutoCreate;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

@Name("manterGraficoAction")
@AutoCreate
@Scope(ScopeType.CONVERSATION)
public class ManterGraficoAction {

	public void fazGrafico(OutputStream out, Object data) throws Exception {
		BufferedImage pageImage = obterGraficoPizza();
		pageImage.flush();
		ImageIO.write(pageImage, "jpeg", out);
	}

	private static BufferedImage obterGraficoPizza() {
		BufferedImage buff = null;

		try {
			
			DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		      dataset.addValue( 15 , "schools" , "1970" );
		      dataset.addValue( 30 , "schools" , "1980" );
		      dataset.addValue( 60 , "schools" ,  "1990" );
		      dataset.addValue( 120 , "schools" , "2000" );
		      dataset.addValue( 240 , "schools" , "2010" );
		      dataset.addValue( 300 , "schools" , "2014" );
		     
			JFreeChart chart = ChartFactory.createLineChart(
			         "teste",
			         "Years","Number",
			         dataset,
			         PlotOrientation.VERTICAL,
			         true,true,false);
			
			chart.setBackgroundPaint(Color.white);
			chart.setBorderVisible(true);
			chart.setBorderPaint(Color.blue);
			chart.setNotify(true);

			buff = chart.createBufferedImage(600, 350,
					BufferedImage.TYPE_INT_RGB, null);
			buff.flush();
			
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buff;
	}
}
