# Auto-generated by EclipseNSIS Script Wizard
# 15/12/2009 3:48:14 PM

Name " ${INSTRUMENT_LABEL} ${APP_VERSION}"

# General Symbol Definitions
!define REGKEY "SOFTWARE\$(^Name)"
!define VERSION ${APP_VERSION}
!define COMPANY "Bragg Institute"
!define URL http://gumtree.codehaus.org

# MUI Symbol Definitions
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\modern-install-full.ico"
!define MUI_FINISHPAGE_NOAUTOCLOSE
!define MUI_STARTMENUPAGE_REGISTRY_ROOT HKLM
!define MUI_STARTMENUPAGE_NODISABLE
!define MUI_STARTMENUPAGE_REGISTRY_KEY ${REGKEY}
!define MUI_STARTMENUPAGE_REGISTRY_VALUENAME StartMenuGroup
!define MUI_STARTMENUPAGE_DEFAULTFOLDER Gumtree
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\modern-uninstall-full.ico"
!define MUI_UNFINISHPAGE_NOAUTOCLOSE

# Included files
!include "FileFunc.nsh"
!include Sections.nsh
!include MUI2.nsh
!include x64.nsh

# Variables
Var StartMenuGroup

# Installer pages
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_STARTMENU Application $StartMenuGroup
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

# Installer languages
!insertmacro MUI_LANGUAGE English

# Installer attributes
OutFile ${INSTRUMENT_LABEL}WorkbenchSetup-${VERSION}-win64.exe
# Enable 64-bit, see: http://bojan-komazec.blogspot.com.au/2011/10/nsis-installer-for-64-bit-windows.html
InstallDir "$PROGRAMFILES64\Gumtree\${INSTRUMENT_LABEL} ${VERSION}"
CRCCheck on
XPStyle on
ShowInstDetails show
VIProductVersion ${APP_VERSION}.0
VIAddVersionKey ProductName "Gumtree ${INSTRUMENT_LABEL}"
VIAddVersionKey ProductVersion "${VERSION}"
VIAddVersionKey CompanyName "${COMPANY}"
VIAddVersionKey CompanyWebsite "${URL}"
VIAddVersionKey FileVersion "${VERSION}"
VIAddVersionKey FileDescription ""
VIAddVersionKey LegalCopyright ""
InstallDirRegKey HKLM "${REGKEY}" Path
ShowUninstDetails show

# Installer sections
Section -Main SEC0000
    SetOutPath $INSTDIR
    SetOverwrite on
    # Enable 64-bit registry, see: http://bojan-komazec.blogspot.com.au/2011/10/nsis-installer-for-64-bit-windows.html
    SetRegView 64
    File /r ${SOURCE}\*
    SetOutPath $INSTDIR\win64
    CreateShortcut "$INSTDIR\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk" $INSTDIR\win64\gumtree.exe "-data @user.home/Gumtree/${INSTRUMENT}/experiment" 
    SetOutPath $INSTDIR
    # Grant access
    AccessControl::GrantOnFile "$INSTDIR" "(S-1-5-32-545)" "FullAccess"
    WriteRegStr HKLM "${REGKEY}\Components" Main 1
SectionEnd

Section -post SEC0001    
    WriteRegStr HKLM "${REGKEY}" Path $INSTDIR
    SetOutPath $INSTDIR
    WriteUninstaller $INSTDIR\${INSTRUMENT_LABEL}Uninstall.exe
    !insertmacro MUI_STARTMENU_WRITE_BEGIN Application
    # For all users
    SetShellVarContext all
    SetOutPath $SMPROGRAMS\$StartMenuGroup
    CopyFiles "$INSTDIR\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk" "$DESKTOP\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk"
    CopyFiles "$INSTDIR\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk" "$SMPROGRAMS\$StartMenuGroup\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk"
    Delete "$INSTDIR\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk"
    CreateShortcut "$SMPROGRAMS\$StartMenuGroup\Uninstall $(^Name).lnk" $INSTDIR\${INSTRUMENT_LABEL}Uninstall.exe
    # [TONY][2012-03-26] We no longer use -type option
    ## Replace .ini file based on type
    #${GetOptions} $CMDLINE "-type" $R0
    #CopyFiles $INSTDIR\ini\${INSTRUMENT}.$R0.ini $INSTDIR\${INSTRUMENT}.ini
    !insertmacro MUI_STARTMENU_WRITE_END
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayName "$(^Name)"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayVersion "${VERSION}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" Publisher "${COMPANY}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" URLInfoAbout "${URL}"
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" DisplayIcon $INSTDIR\${INSTRUMENT_LABEL}Uninstall.exe
    WriteRegStr HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" UninstallString $INSTDIR\${INSTRUMENT_LABEL}Uninstall.exe
    WriteRegDWORD HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" NoModify 1
    WriteRegDWORD HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)" NoRepair 1
SectionEnd

# Macro for selecting uninstaller sections
!macro SELECT_UNSECTION SECTION_NAME UNSECTION_ID
    Push $R0
    ReadRegStr $R0 HKLM "${REGKEY}\Components" "${SECTION_NAME}"
    StrCmp $R0 1 0 next${UNSECTION_ID}
    !insertmacro SelectSection "${UNSECTION_ID}"
    GoTo done${UNSECTION_ID}
next${UNSECTION_ID}:
    !insertmacro UnselectSection "${UNSECTION_ID}"
done${UNSECTION_ID}:
    Pop $R0
!macroend

# Uninstaller sections
Section /o -un.Main UNSEC0000
    RmDir /r /REBOOTOK $INSTDIR
    DeleteRegValue HKLM "${REGKEY}\Components" Main
SectionEnd

Section -un.post UNSEC0001
    # For all users
    SetShellVarContext all
    DeleteRegKey HKLM "SOFTWARE\Microsoft\Windows\CurrentVersion\Uninstall\$(^Name)"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\Uninstall $(^Name).lnk"
    Delete /REBOOTOK "$SMPROGRAMS\$StartMenuGroup\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk"
    Delete /REBOOTOK $INSTDIR\${INSTRUMENT_LABEL}Uninstall.exe
    Delete /REBOOTOK "$DESKTOP\${INSTRUMENT_SHORTCUT} ${VERSION}.lnk"
    DeleteRegValue HKLM "${REGKEY}" StartMenuGroup
    DeleteRegValue HKLM "${REGKEY}" Path
    DeleteRegKey /IfEmpty HKLM "${REGKEY}\Components"
    DeleteRegKey /IfEmpty HKLM "${REGKEY}"
    RmDir /REBOOTOK $SMPROGRAMS\$StartMenuGroup
    RmDir /REBOOTOK $INSTDIR
SectionEnd

# Installer functions
Function .onInit
    InitPluginsDir
FunctionEnd

# Uninstaller functions
Function un.onInit
    ReadRegStr $INSTDIR HKLM "${REGKEY}" Path
    !insertmacro MUI_STARTMENU_GETFOLDER Application $StartMenuGroup
    !insertmacro SELECT_UNSECTION Main ${UNSEC0000}
FunctionEnd
