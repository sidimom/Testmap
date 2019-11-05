package my.test.testmap;

import java.io.Serializable;
import java.util.List;

public class Region implements Serializable {

    public String name;
    public List<Region> subregions;
    public boolean isDownloaded;
    public String download_suffix = "";
    public String inner_download_suffix = "";
    public String download_prefix = "";
    public String inner_download_prefix = "";


}
