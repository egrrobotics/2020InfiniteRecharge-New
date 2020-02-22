/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * Add your docs here.
 */
public class WofMatchResult {

    private final String color;
    private final double confidence;

    public WofMatchResult(String colorString, Double colorConfidence) {
        color = colorString;
        confidence = colorConfidence;
    }

    public double getConfidence() {
        return confidence;
    }

    public String getColor() {
        return color;
    }

}
