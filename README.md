# Auto-seeding-Myclip
Dự án test automation với appium và java.

Viết script chạy auto seeding cho app Myclip, kết nối với thiết bị android:  

**1. Kiểm tra dự án**

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

**2. Kiểm tra devices**

Chạy lệnh `adb devices` để kiểm tra device đang kết nối trên máy tính.
- Nếu kết quả hiển thị <mã thết bị - device>/`emulator-5554   device`, có thể bắt đầu test
- Nếu kết quả hiển thị <mã thết bị - authorizing>/`emulator-5554   authorizing`, tức là thiết bị chưa được cấp quyền kết nối. Thử các cách sau:
  - Khởi động lại adb:
    - `adb kill-server`<br>
      `adb start-server`<br>
      `adb devices`
  - Xóa quyền ADB cũ và kết nối lại:
    - Trên Android, vào Settings → Developer options → Revoke USB debugging authorizations
    - Sau đó ngắt kết nối → kết nối lại → chấp nhận lại quyền ADB khi được hỏi.
- Nếu kết quả hiển thị <mã thết bị - offline>/`emulator-5554   offline`, ADB đã nhận ra thiết bị nhưng không thể giao tiếp được với nó. Thử các cách sau:
  - Khởi động lại adb:
    - `adb kill-server`<br>
      `adb start-server`<br>
      `adb devices`
  - Tắt emulator hoàn toàn và khởi động lại
  - Xóa cache ADB trong emulator:
    - Mở trình quản lý file → xóa file sau (nếu có):
      - `~/.android/adbkey`<br>
        `~/.android/adbkey.pub`
    - Khởi động lại emulator → chạy: `adb devices`
    - Chấp nhận yêu cầu xác nhận (authorize) trên emulator nếu hiện ra.
  - Thử tạo emulator mới: 
    - Xóa emulator cũ đi
    - Tạo emulator mới
    - Khởi động lại emulator → chạy: `adb devices`

**3. Cấu hình thiết bị**
- Thiết bị thật: kết nối qua dây cáp USB, cấu hình cài đặt theo hướng dẫn trong folder **Pre_Run_Tool** file **BuildTest_Window.zip**.
- Thiết bị ảo: cài đặt Android Studio và tải thêm các emulator, khi đó thiết bị sẽ có các emulator ảo có thể sử dụng để dùng với appium.

![image](https://github.com/user-attachments/assets/469292c5-6f89-4997-a376-0e7bc61c6112)
![image](https://github.com/user-attachments/assets/ad234c01-bb6f-4981-9eef-c4b5eed0d0b4)
