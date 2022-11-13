// Allow for only retrieving settings
/**
 * This interface only allows for retrieving settings
 * @author Chang Dao Zheng
 * @version 1.0.0 Nov 13, 2022
 */
public interface SysSettings {
    /**
     * Retrieves the settings of 'list-by'
     * @return settings for 'list-by from SettingsStore'
     */
    default String getListBy() {
        return SettingStore.getInstance().getSetting("list-by");
    }
}
