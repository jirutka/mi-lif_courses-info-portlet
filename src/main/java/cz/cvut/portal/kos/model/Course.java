/*
 * The MIT License
 *
 * Copyright 2013 Jakub Jirutka <jakub@jirutka.cz>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package cz.cvut.portal.kos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Studijní předmět
 * 
 * <p>V programátorské řeči, toto je „třída“ předmětu. Konkrétní předmět vypsaný
 * v semestru, který si studenti zapisují, se v KOSapi nazývá <i>instance 
 * předmětu</i> a reprezentuje ho entita {@link Coursin} (složenina slov Course
 * a Instance).</p>
 * 
 * @author Jakub Jirutka <jakub@jirutka.cz>
 */
public class Course implements Serializable {

    private Integer allowedEnrollmentCount;
    private Date approvalDate;
    private String classesLang;
    private Set<String> classesType;
    private String code;
    private String completion;
    private Integer credits;
    private String department;
    private String homepage;
    private String keywords;
    private String name;
    private String note;
    private String range;
    private String season;
    private String studyForm;

    private String description;
    private String lecturesContents;
    private String literature;
    private String objectives;
    private String requirements;
    private String tutorialsContents;

    
    /**
     * Povolený počet zapsání předmětu určuje, kolikrát za studium si student
     * může daný předmět zapsat (typicky 2x).
     *
     * @return povolený počet zapsání předmětu [max 99]
     */
    public Integer getAllowedEnrollmentCount() { return allowedEnrollmentCount; }
    /**
     * @return datum schválení předmětu
     */
    public Date getApprovalDate() { return approvalDate; }
    /**
     * @return jazyk, ve kterém probíhá výuka
     */
    public String getClassesLang() { return classesLang; }

    /**
     * Metody výuky předmětu, tzn. zda má předmět přednášky, cvičení apod.
     *
     * @return metody výuky předmětu
     */
    public Set<String> getClassesTypes() { return classesType; }

    /**
     * @return unikátní kód předmětu [max 20 znaků]
     */
    public String getCode() { return code; }
    /**
     * @return způsob zakončení předmětu
     */
    public String getCompletion() { return completion; }
    /**
     * @return počet kreditů za absolvování předmětu [max 999]
     */
    public Integer getCredits() { return credits; }
    /**
     * @return středisko, pod které předmět patří
     */
    public String getDepartment() { return department; }
    /**
     * @return
     */
    public String getHomepage() { return homepage; }
    /**
     * @return klíčová slova charakterizující předmět oddělená čárkami
     *         [max 500 znaků]
     */
    public String getKeywords() { return keywords; }
    /**
     * @return název předmětu [max 100 znaků]
     */
    public String getName() { return name; }
    /**
     * @return poznámka [max 2 000 znaků]
     */
    public String getNote() { return note; }
    /**
     * Rozsah předmětu, udává časovou dotaci výukových hodin.
     *
     * <p>Obsahuje hodnoty typu: [0-9]+[dpcslj](\+[0-9]+[pcslj]?)?;
     * Nelze jednoznačně strojově dekódovat, protože se tu míchají hodnoty
     * počet/měsíc a počet/semestr bez nějakého odlišení.</p>
     *
     * @return rozsah předmětu [max 5 znaků]
     */
    public String getRange() { return range; }
    /**
     * @return část školního roku, ve kterém se předmět vypisuje (letní|zimní|oba)
     */
    public String getSeason() { return season; }
    /**
     * @return forma studia, pro kterou je předmět určen
     */
    public String getStudyForm() { return studyForm; }


    /**
     * @return anotace předmětu
     */
    public String getDescription() { return description; }
    /**
     * @return osnova přednášek
     */
    public String getLecturesContents() { return lecturesContents; }
    /**
     * @return seznam doporučené literatury
     */
    public String getLiterature() { return literature; }
    /**
     * @return cíle předmětu
     */
    public String getObjectives() { return objectives; }
    /**
     * @return požadavky
     */
    public String getRequirements() { return requirements; }
    /**
     * @return osnova cvičení
     */
    public String getTutorialsContents() { return tutorialsContents; }

}
