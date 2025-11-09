package com.optimagrowth.license.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;

@Service
public class LicenseService {

	@Autowired
	MessageSource messages;

	@Autowired
	private LicenseRepository licenseRepository;

	public License getLicense(String licenseId, String organizationId) {

		return licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId)
				.orElseThrow(() -> new RuntimeException("License not found"));
	}

	public String createLicense(License license, String organizationId, Locale locale) {
		String responseMessage = null;
		if (!StringUtils.isEmpty(license)) {
			license.setOrganizationId(organizationId);
			licenseRepository.save(license);
			responseMessage = String.format(messages.getMessage("license.create.message", null, locale),
					license.toString());
		}

		return responseMessage;
	}

	public String updateLicense(License license, String organizationId) {
		String responseMessage = null;
		if (!StringUtils.isEmpty(license)) {
			license.setOrganizationId(organizationId);
			responseMessage = String.format(messages.getMessage("license.update.message", null, null),
					license.toString());
		}

		return responseMessage;
	}

	public String deleteLicense(String licenseId, String organizationId) {
		String responseMessage = null;
		responseMessage = String.format(messages.getMessage("license.delete.message", null, null), licenseId,
				organizationId);
		return responseMessage;

	}
}
