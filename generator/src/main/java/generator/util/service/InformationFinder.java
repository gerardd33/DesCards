package generator.util.service;

public interface InformationFinder {

	String findInformation(String query);

	String findInformation(String query, String specialField);
}
