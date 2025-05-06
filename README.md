# Auto-seeding-Myclip
Dự án test automation với appium và java.

Viết script chạy auto seeding cho app Myclip, kết nối với thiết bị android:  

1. Kiểm tra dự án

Trước khi run test
- Cài đặt appium và các driver & plugin appium:
  - Có thể run trực tiếp file InstallAppium.bat trong project, file đã cấu hình sẵn các lệnh cài đặt
  - Cài đặt theo hướng dẫn trên trang: [_driver appium_](https://appium.io/docs/en/2.3/ecosystem/drivers/), [_plugin appium_](https://appium.io/docs/en/2.3/ecosystem/plugins/)
    - Driver appium: `uiautomator2` và `xcuitest`
    - Plugin appium: `execute-driver` hoặc `images`
  - Kiểm tra driver và plugin đã cài:
    - Driver appium: `appium driver list`
    - Plugin appium: `appium plugin list`

- Chạy cmd lệnh `appium --use-plugins=execute-driver --base-path /wd/hub` để kết nối với appium

2. Cấu hình thiết bị
- Thiết bị thật: kết nối qua dây cáp USB, cấu hình cài đặt theo hướng dẫn trong folder **Pre_Run_Tool** file **BuildTest_Window.zip**.
- Thiết bị ảo: cài đặt Android Studio và tải thêm các emulator, khi đó thiết bị sẽ có các emulator ảo có thể sử dụng để dùng với appium.

![image](https://github.com/user-attachments/assets/469292c5-6f89-4997-a376-0e7bc61c6112)
![image](https://github.com/user-attachments/assets/ad234c01-bb6f-4981-9eef-c4b5eed0d0b4)
