package com.example.admin.qwerty;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Парсер групп БГУИР
 */
public class GroupUtility {

    /**
     * Url - адрес получения групп
     */
    public static final String URL = "http://www.bsuir.by/schedule/rest/studentGroup";

    /**
     * Id группы
     */
    public static final String ID = "id";

    /**
     * Название группы
     */
    public static final String NAME = "name";

    /**
     * Курс
     */
    public static final String COURSE = "course";

    /**
     * Id факультета
     */
    public static final String FACULTY_ID = "facultyId";

    /**
     * Form Id
     */
    public static final String SPECIALITY_DEPARTMENT_EDUCATION_FORM_ID = "specialityDepartmentEducationFormId";

    /**
     * Группа
     */
    public static final String STUDENT_GROUP = "studentGroup";

    /**
     * Все группы
     */
    private static HashMap<String, Group> studentGroup = new HashMap<>();

    /**
     * Получить все группы
     * @return список групп
     * @throws IOException ошибка сервера
     * @throws XmlPullParserException ошибка парсера
     */
    public static HashMap<String, Group> getGroupsId() throws IOException, XmlPullParserException {
        try {
            URL url = new URL(URL);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput((url.openStream()), null);

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_TAG:
                        if (isGroupTag(xpp)) {
                            Group group = getGroup(xpp);
                            studentGroup.put(group.getName(), group);
                        }
                        break;
                }
                xpp.next();
            }
            return studentGroup;
        } catch (XmlPullParserException | IOException e) {
            throw e;
        }
    }

    private static Group getGroup(XmlPullParser xpp) throws XmlPullParserException, IOException {
        Group group = new Group();
        do {
            xpp.nextTag();
            switch (xpp.getName()) {
                case ID:
                    group.setId(Integer.parseInt(xpp.nextText()));
                    break;
                case NAME:
                    group.setName(xpp.nextText());
                    break;
                case COURSE:
                    group.setCourse(Integer.parseInt(xpp.nextText()));
                    break;
                case FACULTY_ID:
                    group.setFacultyId(Integer.parseInt(xpp.nextText()));
                    break;
                case SPECIALITY_DEPARTMENT_EDUCATION_FORM_ID:
                    group.setSpecialityDepartmentEducationFormId(Integer.parseInt(xpp.nextText()));
                    break;
            }
        } while (!isGroupTag(xpp));

        return group;
    }

    private static boolean isGroupTag(XmlPullParser xpp) {
        return xpp.getName().equals(STUDENT_GROUP);
    }
}
