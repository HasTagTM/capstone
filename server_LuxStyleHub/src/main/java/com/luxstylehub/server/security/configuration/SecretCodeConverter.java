package com.luxstylehub.server.security.configuration;

import jakarta.persistence.AttributeConverter;

public class SecretCodeConverter implements AttributeConverter<String, String> {
	
	Character[] arrChar = {'P', 'B', '@', 'R', 'X', '!', 'H', 'W', '$', 'U'};

	@Override
	public String convertToDatabaseColumn(String secretCode) {
		//"3927" -> "RU@W"
		String code = "";

		if(secretCode == null) {
			return code;
		}
		
		for (Character c : secretCode.toCharArray()) {
			// '3' '9' '2' '7'
			int index = Integer.parseInt(c.toString());
			code += arrChar[index];
		}
		
		return code; // "RU@W"
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		//"RU@W" -> "3927"
		String code = "";
		
		if(dbData == null) {
			return code;
		}
		
		for (Character c : dbData.toCharArray()) {
			// 'R' 'U' '@' 'W'
			for (int i = 0; i < arrChar.length; i++) {
				if(arrChar[i].equals(c)) {
					code += i; // 3 9 2 7 
				}
			}
		}
		
		return code; // "3927"
	}

}
