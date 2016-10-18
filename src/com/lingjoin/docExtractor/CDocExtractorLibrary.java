package com.lingjoin.docExtractor;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CDocExtractorLibrary extends Library {

	CDocExtractorLibrary Instance = (CDocExtractorLibrary) Native.loadLibrary("DocExtractor", CDocExtractorLibrary.class);
	
	public int DE_Init(String arg,int encode,String licenseCode);
	
	public Long DE_ParseDocE(String sText,String sUserDefPos,boolean bSummaryNeeded, int nFuncRequired);
	
	public String DE_GetResult(Long handle, int nDocExtractType);
	
	public int DE_GetSentimentScore(Long handle);
	
	public void DE_ReleaseHandle(Long handle);
	
	public int DE_ComputeSentimentDoc(String sText);
	
	public boolean DE_Exit();
	
	public String DE_GetLastErrMsg();
	
	public int DE_ImportSentimentDict(String sFilename);
	
	public int DE_ImportUserDict(String sFilename);
	
	public int DE_ImportKeyBlackList(String sFilename);
	
	/**
	 * 输出的人名
	 */
	public static final int DOC_EXTRACT_TYPE_PERSON = 0;
	/**
	 * 输出的地名
	 */
	public static final int DOC_EXTRACT_TYPE_LOCATION = 1; 
	/**
	 *  输出的机构名
	 */
	public static final int DOC_EXTRACT_TYPE_ORGANIZATION = 2;
	/**
	 *  输出的关键词
	 */
	public static final int DOC_EXTRACT_TYPE_KEYWORD = 3;
	/**
	 * 输出的文章作者
	 */
	public static final int DOC_EXTRACT_TYPE_AUTHOR = 4;
	/**
	 *  输出的媒体
	 */
	public static final int DOC_EXTRACT_TYPE_MEDIA = 5;
	/**
	 *  输出的文章对应的所在国别
	 */
	public static final int DOC_EXTRACT_TYPE_COUNTRY = 6;
	/**
	 * 输出的文章对应的所在省份
	 */
	public static final int DOC_EXTRACT_TYPE_PROVINCE = 7;
	/**
	 * 输出文章的摘要
	 */
	public static final int DOC_EXTRACT_TYPE_ABSTRACT = 8; 
	/**
	 * 输出文章的正面情感词为
	 */
	public static final int DOC_EXTRACT_TYPE_POSITIVE = 9;
	/**
	 * 输出文章的副面情感词
	 */
	public static final int DOC_EXTRACT_TYPE_NEGATIVE = 10;
	/**
	 * 输出文章去除网页等标签后的正文
	 */
	public static final int DOC_EXTRACT_TYPE_DEL_HTML = 11;
	/**
	 * 用户自定义词，其中：
	 * DOC_EXTRACT_TYPE_USER_DEFINED + 1:表示自定义的第一类词;
	 * DOC_EXTRACT_TYPE_USER_DEFINED + 2:表示自定义的第二类词;
	 * DOC_EXTRACT_TYPE_USER_DEFINED + 3:表示自定义的第三类词;
	 * DOC_EXTRACT_TYPE_USER_DEFINED + 4:表示自定义的第四类词;
	 * ...以此类推。
	 */
	public static final int DOC_EXTRACT_TYPE_USER_DEFINED = 11;

	public static final int PERSON_REQUIRED = 0x0001;
	public static final int LOCATION_REQUIRED = 0x0002;
	public static final int ORGANIZATION_REQUIRED = 0x0004;
	public static final int KEYWORD_REQUIRED = 0x0008;
	public static final int AUTHOR_REQUIRED = 0x0010;
	public static final int MEDIA_REQUIRED = 0x0100;
	public static final int COUNTRY_REQUIRED = 0x0200;
	public static final int PROVINCE_REQUIRED = 0x0400;
	public static final int ABSTRACT_REQUIRED = 0x0800;
	public static final int TRANS_REQUIRED = 0x1000;
	public static final int FOOD_REQUIRED = 0x2000;
	public static final int APPS_REQUIRED = 0x4000;
	public static final int SENTIMENT_REQUIRED = 0x8000;
	public static final int ALL_REQUIRED = 0xffff;

}
