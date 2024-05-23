package controller;

import model.Feedbacks;
import model.FeedbacksDAO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Servlet implementation class ExportFeedbacksExcelServlet
 */
@WebServlet("/ExportFeedbacksExcelServlet")
public class ExportFeedbacksExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportFeedbacksExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=feedbacks.xlsx");

        try (Workbook workbook = new XSSFWorkbook(); OutputStream out = response.getOutputStream()) {
            Sheet sheet = workbook.createSheet("Feedbacks");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Feedback ID");
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Description");
            headerRow.createCell(3).setCellValue("Date");
            headerRow.createCell(4).setCellValue("Client Name");
            headerRow.createCell(5).setCellValue("Product Name");

            // Get feedback data
            FeedbacksDAO dao = FeedbacksDAO.instance;
            List<Feedbacks> feedbacksList = dao.getAllFeedbacks(); 

            int rowNum = 1;
            for (Feedbacks feedback : feedbacksList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(feedback.getFeedbackId());
                row.createCell(1).setCellValue(feedback.getTitle());
                row.createCell(2).setCellValue(feedback.getDescription());
                row.createCell(3).setCellValue(feedback.getDate());
                row.createCell(4).setCellValue(feedback.getClientName());
                row.createCell(5).setCellValue(feedback.getProductName());
            }

            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
