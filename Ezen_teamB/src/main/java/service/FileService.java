package service;

import java.io.File;

public class FileService {
	// 1. 파일 삭제
		public static boolean fileDelete( String filepath ) {
			
			// 1. 파일경로의 파일객체 선언 [ 다양한 메소드 제공하니까 ]
			File file = new File(filepath);
			if( file.exists()) {	// 만약에 경로상의 파일이 존재하면
				file.delete();	// 경로상의 파일 삭제
				return true;
			}
			
				
			return false;
		}
}
