public class Vipolnayushiy {
    private String doctorStatus;  // Может быть "доктор" или "медсестра"
    private String doctorName;


    public Vipolnayushiy(String doctorName, String doctorStatus) {
        this.doctorName = doctorName;
        this.doctorStatus = doctorStatus;
    }

    // Получить имя доктора
    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorStatus() {
        return doctorStatus;
    }
}

