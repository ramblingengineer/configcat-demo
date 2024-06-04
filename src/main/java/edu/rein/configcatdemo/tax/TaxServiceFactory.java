package edu.rein.configcatdemo.tax;

public class TaxServiceFactory {
    static Tax2024Service tax2024Service = new Tax2024Service();
    static TaxPre2024Service taxPre2024Service = new TaxPre2024Service();
    public static TaxDelegateService taxService = new TaxDelegateService();
}
