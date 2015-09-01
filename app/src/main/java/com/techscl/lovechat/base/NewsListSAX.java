package com.techscl.lovechat.base;

import android.os.Handler;
import android.os.Message;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/8/3.
 */
public class NewsListSAX extends DefaultHandler {
    private List<News> newsList;
    private News currNews;
    private String preTag;
    private Handler handler;

    public NewsListSAX(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
//        Log.i("tag","startDocument");
        newsList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
//        Log.i("tag", "startElement");
        if ("item".equals(localName)) {
            currNews = new News();
        }
        preTag = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
//        Log.i("tag", "characters");
        String data = new String(ch, start, length);
        if ("newsid".equals(preTag)) {
            currNews.setNewsid(data);
        } else if ("title".equals(preTag)) {
            currNews.setTitle(data);
        } else if ("url".equals(preTag)) {
            currNews.setUrl(data);
        } else if ("postdate".equals(preTag)) {
            currNews.setPostdate(data);
        } else if ("image".equals(preTag)) {
            currNews.setImage(data);
        } else if ("description".equals(preTag)) {
            currNews.setDescription(data);
        } else if ("hitcount".equals(preTag)) {
            currNews.setHitcount(data);
        } else if ("commentcount".equals(preTag)) {
            currNews.setCommentcount(data);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
//        Log.i("tag", "endElement");
        if ("item".equals(localName)) {
            newsList.add(currNews);
            currNews = null;
        }
        preTag = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
//        Log.i("tag", "endDocument");

        Message message = handler.obtainMessage();
        message.what = 10;
        message.obj = newsList;
        handler.sendMessage(message);

    }


}
