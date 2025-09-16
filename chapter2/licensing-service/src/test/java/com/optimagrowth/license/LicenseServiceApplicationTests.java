package com.optimagrowth.license;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.repository.OrganizationRepository;

@SpringBootTest
class LicenseServiceApplicationTests {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Test
	@Transactional
	public void testOrganizationReport() {
		List<Organization> organizations = organizationRepository.findAll();
		assertNotNull(organizations, "Organizations should not be null");

		System.out.println("Organization Report:");
		for (Organization organization : organizations) {
			int numberOfStudents = organization.getMembers().size();
			System.out.printf("Organization: %s, Category: %s, Number of Students: %d%n",
					organization.getName(),
					organization.getCategory(),
					numberOfStudents);
		}
	}

}
