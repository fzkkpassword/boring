package dosomething;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Properties;

class MessageOfDay {
    private final Properties festive = new Properties();
    private final LocalDate dateOfChina;

    public MessageOfDay() {
        this.setFestive();
        this.dateOfChina = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toLocalDate();
    }

    public void setFestive() {
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/festive.properties");
            this.festive.load(inputStream);
        } catch (IOException var9) {
            IOException e = var9;
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException var10) {
                IOException e = var10;
                throw new RuntimeException(e);
            }

        }

    }

    public Properties getFestive() {
        return this.festive;
    }

    public LocalDate getDateOfChina() {
        return this.dateOfChina;
    }
}
