package model;

public class TestTable {

  String companyName;

  String industryCompany;

  String department;

  String pamsid;

  String projectName;

  String principal;

  String bin_time;

  String bidAmount;

  String state;

  String projectType;

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getIndustryCompany() {
    return industryCompany;
  }

  public void setIndustryCompany(String industryCompany) {
    this.industryCompany = industryCompany;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getPamsid() {
    return pamsid;
  }

  public void setPamsid(String pamsid) {
    this.pamsid = pamsid;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public String getBin_time() {
    return bin_time;
  }

  public void setBin_time(String bin_time) {
    this.bin_time = bin_time;
  }

  public String getBidAmount() {
    return bidAmount;
  }

  public void setBidAmount(String bidAmount) {
    this.bidAmount = bidAmount;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getProjectType() {
    return projectType;
  }

  public void setProjectType(String projectType) {
    this.projectType = projectType;
  }

  @Override
  public String toString() {
    return "TestTable{" +
        "companyName='" + companyName + '\'' +
        ", industryCompany='" + industryCompany + '\'' +
        ", department='" + department + '\'' +
        ", pamsid='" + pamsid + '\'' +
        ", projectName='" + projectName + '\'' +
        ", principal='" + principal + '\'' +
        ", bin_time='" + bin_time + '\'' +
        ", bidAmount='" + bidAmount + '\'' +
        ", state='" + state + '\'' +
        ", projectType='" + projectType + '\'' +
        '}';
  }
}
