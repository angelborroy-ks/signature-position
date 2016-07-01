package es.keensoft.pdf.sig;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.util.List;

public class GetSigPos {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream file = new FileInputStream(new File(GetSigPos.class.getClassLoader().getResource("Signed-Document.pdf").getFile()));
		PdfReader reader = new PdfReader(file);

		AcroFields fields = reader.getAcroFields();
		
		for (String signame : fields.getSignatureNames()) {
			
			List<AcroFields.FieldPosition> positions = fields.getFieldPositions(signame);
			Rectangle rect = positions.get(0).position;
			float left = rect.getLeft();
			float bTop = rect.getTop();
			float width = rect.getWidth();
			float height = rect.getHeight();

			int page = positions.get(0).page;
			Rectangle pageSize = reader.getPageSize(page);
			float pageHeight = pageSize.getTop();
			float top = pageHeight - bTop;

			System.out.print(signame + "::" + page + "::" + left + "::" + top + "::" + width + "::" + height + "\n");
			
		}
		
	}
	
}