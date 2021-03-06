package messageNotification.ExcelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;


public class DataExtract {
	
	private final static String MOBILE_NUMBER_COLUMN_NAME = "mobile_number";
	
	private Workbook workbook;
	private Sheet sheet;
	
	public DataExtract(MultipartFile file) throws Exception {
		super();
		try {
			this.workbook = new XSSFWorkbook(file.getInputStream());
			this.sheet = workbook.getSheetAt(0);
		}catch (Exception e) {
			throw new Exception("Unable to upload file: or file may be corrupted");
		}		
	}
	
	//gets the column index in the excel sheet that contains the mobile numbers
	public int getPhoneNumberColumnIndex() {
		Row headings = this.sheet.getRow(0);
		int phoneNumberColumnIndex = -1;
		for(Cell cell: headings) {
			if(cell.getStringCellValue().equals(MOBILE_NUMBER_COLUMN_NAME)) {
				phoneNumberColumnIndex = cell.getColumnIndex();
				break;
			}
		}
		return phoneNumberColumnIndex;
	}
	
	//returns all the mobile numbers in the excel sheet
	public Set<String> getAllMobileNumbers(){
		Set<String> mobileNumbers = new HashSet<>();
		for(Row row: this.sheet) {
			if(row.getRowNum()==0) {
				continue;
			}
			mobileNumbers.add(String.valueOf(row.getCell(this.getPhoneNumberColumnIndex()).getNumericCellValue()));
		}
		return mobileNumbers;
	}
	
}
