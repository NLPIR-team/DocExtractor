package com.lingjoin.docExtractor;


public class DocExtractor {

	public static boolean state=false;
	
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
	
	public static boolean init(String arg){
		if(CDocExtractorLibrary.Instance.DE_Init(arg, 1, "")==1)
			state=true;
		else
			state=false;
		//System.out.println("DocExtractor："+CDocExtractorLibrary.Instance.DE_Init(arg, 1, ""));
		if(state){
			return true;
		}else{
			System.out.println(CDocExtractorLibrary.Instance.DE_GetLastErrMsg());
			return false;
		}
	}
	
	public static Long parseDocE(String sText,String sUserDefPos,boolean bSummaryNeeded, int nFuncRequired){
		if(state){
		    return CDocExtractorLibrary.Instance.DE_ParseDocE(sText, sUserDefPos, bSummaryNeeded, nFuncRequired);
		}else{
			return null;
		}
	}
	
	public static String getResult(Long handle, int nDocExtractType){
		if(state)
			return CDocExtractorLibrary.Instance.DE_GetResult(handle, nDocExtractType);
		else{
			return null;
		}
	}
	
	public static int DE_GetSentimentScore(Long handle){
		if(state)
			return CDocExtractorLibrary.Instance.DE_GetSentimentScore(handle);
		else
			return 0;
	}
	
	public static int computeSentimentDoc(String text){
		if(state)
			return CDocExtractorLibrary.Instance.DE_ComputeSentimentDoc(text);
		else
			return 0;
	}
	
	public static boolean releaseHandle(Long handle){
		if(state){
			CDocExtractorLibrary.Instance.DE_ReleaseHandle(handle);
			return true;
		}
		else
			return false;
	}
	
	public static int importSentimentDict(String dict){
		if(state)
			return CDocExtractorLibrary.Instance.DE_ImportSentimentDict(dict);
		else
			return 0;
	}
	
	public static int importUserDict(String dict){
		if(state)
			return CDocExtractorLibrary.Instance.DE_ImportUserDict(dict);
		else
			return 0;
	}
	
	public static int importKeyBlackList(String dict){
		if(state)
			return  CDocExtractorLibrary.Instance.DE_ImportKeyBlackList(dict);
		else
			return 0;
	}
	
	public static String getLastErrMsg(){
		return CDocExtractorLibrary.Instance.DE_GetLastErrMsg();
	}
	
	public static boolean exit(){
		return CDocExtractorLibrary.Instance.DE_Exit();
	}
}
