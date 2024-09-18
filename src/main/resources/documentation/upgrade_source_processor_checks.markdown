# Checks for .bnd, .ftl, .gradle, .java, .json, .jsp, .jspf, .scss or .vm

Check | Category | Description
----- | -------- | -----------
UpgradeBNDDeclarativeServicesCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Adds `-dsannotations-options: inherit` to `bnd.bnd` if it does not yet exist. |
UpgradeBNDIncludeResourceCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Checks if the property value `-includeresource` or `Include-Resource` exists and removes it. |
UpgradeCatchAllCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Performs replacements on Liferay's outdated code. |
[UpgradeCatchAllJSPImportsCheck](check/jsp_imports_check.markdown#jspimportscheck) | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Sorts and groups imports in `UpgradeCatchAllCheck.testjsp` file. |
UpgradeCatchAllJavaImportsCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Sorts and groups imports in `UpgradeCatchAllCheck.testjava` file. |
UpgradeCatchAllJavaTermOrderCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Sorts javaterms in `UpgradeCatchAllCheck.testjava` file. |
UpgradeGradleIncludeResourceCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces with `compileInclude` the configuration attribute for dependencies in `build.gradle` that are listed at `Include-Resource` property at `bnd.bnd` associated file. |
UpgradeJSPFieldSetGroupCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code to remove 'fieldset-group' tag. |
UpgradeJavaAssetEntryAssetCategoriesCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces methods referring to class `AssetEntryAssetCategory` in class `AssetCategoryLocalService` with equivalent methods in class `AssetEntryAssetCategoryRelLocalService`. |
UpgradeJavaBaseFragmentCollectionContributorExtendedClassesCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Adds FragmentCollectionKey to Component annotation in classes that extend `BaseFragmentCollectionContributor` |
UpgradeJavaBaseModelListenerCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Add parameter in the onAfterUpdate and onBeforeUpdate methods of the BaseModelListener class. |
UpgradeJavaBasePanelAppExtendedClassesCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace the setPortlet method with getPortlet. |
UpgradeJavaCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Performs upgrade checks for `java` files. |
UpgradeJavaCommerceOrderValidatorCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace the parameter Int for BigDecimal of method validate of 'CommerceOrderValidator' interface. |
UpgradeJavaDDMFormValuesSerializerTrackerCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces the references of `DDMFormValuesSerializerTracker` class and also its methods usages. |
UpgradeJavaDisplayPageInfoItemCapabilityCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace all references of DisplayPageInfoItemCapability to InfoItemCapability |
UpgradeJavaFDSActionProviderCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Reorder parameters in the getDropdownItems method of the FDSDataProvider interface. |
UpgradeJavaFDSDataProviderCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Reorder parameters in the getItems and getItemsCount methods of the FDSDataProvider interface. |
UpgradeJavaFacetedSearcherCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces the references of the `Indexer indexer = FacetedSearcher.getInstance();` declaration and `indexer.search` method call. |
UpgradeJavaFinderImplCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Add Component annotation to `*FinderImpl.java` file. |
UpgradeJavaGetFDSTableSchemaParameterCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Fill the new parameter of the method 'getFDSTableSchema' of 'FDSTableSchema'. |
UpgradeJavaGetFileMethodCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of method from 'getFile' to 'getFileAsStream', and include a method 'FileUtil.createTempFile'. |
UpgradeJavaGetLayoutDisplayPageObjectProviderCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace parameter type long by ItemInfoReference in the getLayoutDisplayPageObjectProvider method. |
UpgradeJavaGetLayoutDisplayPageProviderCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace getLayoutDisplayPageProvider by getLayoutDisplayPageProviderByClassName. |
UpgradeJavaLocalServiceImplCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Add Component annotation to `*LocalServiceImpl.java` file. |
UpgradeJavaMultiVMPoolUtilCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces the references of the MultiVMPoolUtil class and also its methods usages. |
UpgradeJavaPortletIdMethodCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace the 'document.get(Field.PORTLET_ID)' by the new interface 'PortletProviderUtil.getPortletId'. |
UpgradeJavaPortletSharedSearchSettingsCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces the Optional return type of the methods `getParameterValues` and `getPortletPreferences` of `PortletSharedSearchSettings` class. |
UpgradeJavaProductDTOConverterReferenceCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Updates references of `ProductDTOConverter` to `DTOConverter` |
UpgradeJavaSchedulerEntryImplConstructorCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace constructors that use the empty constructor of the SchedulerEntryImpl class. |
UpgradeJavaScreenContributorClassCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace class `PortalSettingsConfigurationScreenContributor` by `ConfigurationScreenWrapper` and create an inner class. |
UpgradeJavaServiceImplCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Add Component annotation to `*ServiceImpl.java` file. |
UpgradeJavaServiceReferenceAnnotationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration to replace '@ServiceReference' by '@Reference'. |
UpgradeJavaSortFieldNameTranslatorCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Upgrade class that implements SortFieldNameTranslator. |
UpgradeJavaStorageTypeAwareCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code to delete StorageTypeAware interface. |
UpgradePortletFTLCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Include the CSS classes 'cadmin' and include for impression of 'right cadmin' in 'portlet.ftl' file. |
UpgradeRejectedExecutionHandlerCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace Liferay's RejectedExecutionHandler with Java's RejectedExecutionHandler. |
UpgradeSCSSMixinsCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replace outdated mixins (e.g. media-query, respond-to, etc.). |
UpgradeSCSSNodeSassPatternsCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of Dart Sass deprecated patterns (e.g., the division operation using the '/' character, the interpolation syntax, etc.). |
UpgradeSetResultsSetTotalMethodCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Replaces methods setResults and setTotal from SearchContainer with the method setResultsAndTotal only. |
UpgradeVelocityCommentMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of comments from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityFileImportMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of file import from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityForeachMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of references to Foreach statement from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityIfStatementsMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of references to If statements from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityLiferayTaglibReferenceMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of references to specific Liferay taglib from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityMacroDeclarationMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of references to Macro statement from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityMacroReferenceMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of references to a custom Macro statement from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityVariableReferenceMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of references to variables from a Velocity file to a Freemarker file with the syntax replacements. |
UpgradeVelocityVariableSetMigrationCheck | [Upgrade](upgrade_checks.markdown#upgrade-checks) | Run code migration of set variables from a Velocity file to a Freemarker file with the syntax replacements. |