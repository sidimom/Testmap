package my.test.testmap.MVP;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import my.test.testmap.Region;

public class ModelReadingXML {

    private XmlPullParser parser;

    public ModelReadingXML(XmlPullParser parser) {
        this.parser = parser;
    }

    public List<Region> getRegions() throws XmlPullParserException, IOException {
        List<Region> regions = new ArrayList<>();
        addRegion(parser, regions);
        /*while (parser.getEventType() != XmlPullParser.END_DOCUMENT){

            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("region")){

                int countAttribute = parser.getAttributeCount();
                Region newRegion = new Region();
                for (int i = 0; i < countAttribute; i++) {
                    switch (parser.getAttributeName(i)){
                        case "name":
                            newRegion.name = parser.getAttributeValue(i);
                            break;
                        case "map":
                            newRegion.isDownloaded = parser.getAttributeValue(i).equals("yes");
                            break;
                        case "download_suffix":
                            newRegion.download_suffix = parser.getAttributeValue(i);
                            break;
                        case "inner_download_suffix":
                            newRegion.inner_download_suffix = parser.getAttributeValue(i);
                            break;
                        case "download_prefix":
                            newRegion.download_prefix = parser.getAttributeValue(i);
                            break;
                        case "inner_download_prefix":
                            newRegion.inner_download_prefix = parser.getAttributeValue(i);
                            break;
                        default:
                            break;
                    }
                }
                if (newRegion.inner_download_prefix != null
                        && !newRegion.inner_download_prefix.isEmpty()){
                    newRegion.subregions = addSubregions(parser, newRegion.name);
                }
                regions.add(newRegion);
            }
            parser.next();
        }*/

        return regions;
    }

    private void addRegion(XmlPullParser parser, List<Region> regions) throws XmlPullParserException, IOException {
        parser.next();
        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            if (parser.getEventType() == XmlPullParser.END_TAG){
                return;
            }

            if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("region")){

                int countAttribute = parser.getAttributeCount();
                Region newRegion = new Region();
                for (int i = 0; i < countAttribute; i++) {
                    switch (parser.getAttributeName(i)){
                        case "name":
                            newRegion.name = parser.getAttributeValue(i);
                            break;
                        case "map":
                            newRegion.isDownloaded = parser.getAttributeValue(i).equals("yes");
                            break;
                        case "download_suffix":
                            newRegion.download_suffix = parser.getAttributeValue(i);
                            break;
                        case "inner_download_suffix":
                            newRegion.inner_download_suffix = parser.getAttributeValue(i);
                            break;
                        case "download_prefix":
                            newRegion.download_prefix = parser.getAttributeValue(i);
                            break;
                        case "inner_download_prefix":
                            newRegion.inner_download_prefix = parser.getAttributeValue(i);
                            break;
                        default:
                            break;
                    }
                }
                regions.add(newRegion);
                newRegion.subregions = new ArrayList<>();
                addRegion(parser, newRegion.subregions);
            }
            parser.next();
        }
    }

    private List<Region> addSubregions(XmlPullParser parser, String name) throws XmlPullParserException, IOException {
        List<Region> subregions = new ArrayList<>();
        Stack<String> stackSubRegions = new Stack<>();
        stackSubRegions.push(name);
        while (!stackSubRegions.empty()){
            parser.next();
            if (parser.getEventType() == XmlPullParser.END_TAG){
                stackSubRegions.pop();
            }else if (parser.getEventType() == XmlPullParser.START_TAG
                    && parser.getName().equals("region")){

                int countAttribute = parser.getAttributeCount();
                Region newRegion = new Region();
                for (int i = 0; i < countAttribute; i++) {
                    switch (parser.getAttributeName(i)){
                        case "name":
                            newRegion.name = parser.getAttributeValue(i);
                            break;
                        case "map":
                            newRegion.isDownloaded = parser.getAttributeValue(i).equals("yes");
                            break;
                        case "download_suffix":
                            newRegion.download_suffix = parser.getAttributeValue(i);
                            break;
                        case "inner_download_suffix":
                            newRegion.inner_download_suffix = parser.getAttributeValue(i);
                            break;
                        case "download_prefix":
                            newRegion.download_prefix = parser.getAttributeValue(i);
                            break;
                        case "inner_download_prefix":
                            newRegion.inner_download_prefix = parser.getAttributeValue(i);
                            break;
                        default:
                            break;
                    }
                }

                stackSubRegions.push(newRegion.name);
                subregions.add(newRegion);
            }
        }
        return subregions;
    }
}
