package com.example.android.justjava;


/**
 * Created by suthe_000 on 11/17/2016.
 */

public class ReportCard {


    private int[] currentGrades = new int[7];
    private String mName;


    public ReportCard(String name, int initialGrade) {
        mName = name;
        currentGrades[0] = initialGrade;
        currentGrades[1] = initialGrade;
        currentGrades[2] = initialGrade;
        currentGrades[3] = initialGrade;
        currentGrades[4] = initialGrade;
        currentGrades[5] = initialGrade;
        currentGrades[6] = initialGrade;

        for (int i = 0; i < currentGrades.length; i++) {
            currentGrades[i] = initialGrade;
        }

    }

    //If a student were to get all A's, every class grade can be set once
    public void setAllGrades(int grade) {
        currentGrades[0] = grade;
        currentGrades[1] = grade;
        currentGrades[2] = grade;
        currentGrades[3] = grade;
        currentGrades[4] = grade;
        currentGrades[5] = grade;
        currentGrades[6] = grade;
    }

    public void setName(String newName) {
        mName = newName;
    }

    // Allows course grades to be set individually
    public void setOneGrade(int course, int grade) {
        currentGrades[course] = grade;
    }


    // Returns current grades
    public int[] getGrades() {

        return currentGrades;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {


        return String.format("Name: %s\nGrades: %s %s %s %s %s %s %s", mName, currentGrades[0],
                currentGrades[1],
                currentGrades[2],
                currentGrades[3],
                currentGrades[4],
                currentGrades[5],
                currentGrades[6]);
    }

}
