package com.vasil.raichynets.api.tests.listeners;

import lombok.extern.log4j.Log4j2;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


/**
 * @author Vasil Raychinets
 */

@Log4j2
public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("------- Starting test %s-------\n", iTestResult.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("------- SUCCESS -- %s---------\n", iTestResult.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("!!!!!!!!! FAILURE-- %s-------\n", iTestResult.getMethod().getMethodName()));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
