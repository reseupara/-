package com.reshome.service;

import com.reshome.dto.EmailDTO;

public interface EmailService {

	public void sendMail(EmailDTO dto, String message);
}
