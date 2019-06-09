package site.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public enum LocaleService {
  
    INSTANCE;
    
    private Locale locale;

    private LocaleService() {
        setLanguage(Language.PL);
    }

    public String getMessage(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Bundle", locale);
        return resourceBundle.getString(key);
    }
    
    public void setLanguage(Language language) {
        locale = new Locale(language.name());
    }
    
}
