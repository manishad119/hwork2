

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import semanticweb.OwlParser;

/**
 * Servlet implementation class TagGeneratorServlet
 */
@WebServlet("/TagGeneratorServlet")
public class TagGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagGeneratorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		OwlParser parser1=new OwlParser();
		String result="Tags:";
		Set<String> tagset=new HashSet<String>();
		Set<Integer> unicodeSet=new HashSet<Integer>();
		try {
			
			parser1.init();
			response.flushBuffer();
			String unicodes=request.getParameter("text");
			//System.out.println(unicodes);
			if(unicodes!=null && unicodes.length()>0){
				StringTokenizer token=new StringTokenizer(unicodes,":");
				while(token.hasMoreTokens()){
					String unicode=token.nextToken();
					int unval=Integer.parseInt(unicode);
					if(!unicodeSet.contains(unval)){
						unicodeSet.add(unval);
						ResultSet annot=parser1.findAnnotations(unval);
						while(annot.hasNext()){
							QuerySolution soln=annot.next();
							String annot1=soln.getResource("an").getLocalName();
							if(!tagset.contains(annot1)){
								tagset.add(annot1);
								result=result+"#"+annot1+" ";
							}
							
						}//while annot
					}//if unicodeSet
				}// while token
			}//if unicodes
			
		}
		catch(Exception exp){
			response.getWriter().append(exp.getMessage());
			response.flushBuffer();
			
			exp.printStackTrace();
		}
		//System.out.println(response);
		response.getWriter().append(result);
		response.flushBuffer();
	}

}
