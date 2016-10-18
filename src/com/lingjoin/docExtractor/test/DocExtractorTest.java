package com.lingjoin.docExtractor.test;

import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.lingjoin.docExtractor.DocExtractor;

public class DocExtractorTest {

	List<String> files=new ArrayList<String>();
	
	public void getAllfiles(File filePath){
		File[] fsFiles=filePath.listFiles();
		for(File f:fsFiles){
			if(f.isFile()&&!f.getName().equals(".DS_Store")) files.add(f.getPath());
			if(f.isDirectory()) this.getAllfiles(f);
		}
	}
	
	public String getContent(File file)throws Exception{
		RandomAccessFile f=new RandomAccessFile(file, "r");
		byte[] b=new byte[(int) file.length()];
		f.read(b);
		f.close();
		String c=new String(b,"GBK").replaceAll("\\s", "");
		return c;
	}
	
	public static void main(String[] args)throws Exception{
		DocExtractor.init("lib");
		FileWriter fw=new FileWriter(new File("doc.txt"));
		String fPath="data_train";
		DocExtractorTest test=new DocExtractorTest();
		test.getAllfiles(new File(fPath));
		Long handle=null;
		for(String f:test.files){
			System.out.println(f);
			String con=test.getContent(new File(f));
			handle =DocExtractor.parseDocE(con, "mgc#ngd", false, DocExtractor.ALL_REQUIRED);
			System.out.println(handle);
			fw.write(new File(f).getName()+"\t"+
					DocExtractor.getResult(handle, DocExtractor.DOC_EXTRACT_TYPE_PERSON)+"\t"+
					DocExtractor.getResult(handle, DocExtractor.DOC_EXTRACT_TYPE_COUNTRY)+"\t"+
					DocExtractor.getResult(handle, DocExtractor.DOC_EXTRACT_TYPE_LOCATION)+"\t"+
					DocExtractor.getResult(handle, DocExtractor.DOC_EXTRACT_TYPE_MEDIA)+"\t"+
					DocExtractor.getResult(handle, DocExtractor.DOC_EXTRACT_TYPE_ORGANIZATION)+"\t"
					+"\t"+con+"\n");
		}
		fw.flush();
		fw.close();
	}
}
