## Giới thiệu:
- ✍ Môn học: Lập trình WWW (Java)
- ✍ Chủ đề: Bài tập tuần 06
<br />

## Đề bài:
<img src="img/Screenshot 2023-11-19 094658.png"/>
<img src="img/Screenshot 2023-11-19 094737.png"/>
<br />

## Bài làm:
1. Chức năng đăng nhập
<p></p>
- ✍ Giao diện trang đăng nhập. Nếu chưa có tài khoản có thể chọn Register để đăng ký
<p></p>
<img src="img/Screenshot 2023-11-19 091425.png"/>
2. Chức năng đăng ký
<p></p>
- ✍ Giao diện trang đăng ký. Riêng mật khẩu sẽ được băm trước khi lưu vào cơ sở dữ liệu
<p></p>
- ✍ Khi đăng ký thành công sẽ quay lại trang đăng nhập cho người dùng đăng nhập
<p></p>
<img src="img/Screenshot 2023-11-19 091655.png"/>
3. Chức năng hiển thị và cập nhật thông tin người dùng
<p></p>
- ✍ Giao diện chính khi đăng nhập thành công. Có thanh menu chứa các chức năng cho người dùng
<p></p>
- ✍ Hiển thị cụ thể thông tin của người dùng. Có thể cập nhật lại thông tin
<p></p>
<img src="img/Screenshot 2023-11-19 092055.png"/>
4. Chức năng đổi mật khẩu
<p></p>
- ✍ Hiển thị form yêu cầu nhập mật khẩu hiện tại và mật khẩu mới. Chỉ khi nhập đúng mật khẩu hiện tại mới cho phép đổi mật khẩu
<p></p>
<img src="img/Screenshot 2023-11-19 091945.png"/>
5. Chức năng thêm bài viết
<p></p>
- ✍ Hiển thị form cho người dùng điền các thông tin cần thiết của bài viết
<p></p>
<img src="img/Screenshot 2023-11-19 092153.png"/>
<img src="img/Screenshot 2023-11-19 092211.png"/>
- ✍ Khi điền đầy đủ thông tin và chọn Post thì sẽ chuyển sang trang My Posts hiển thị các bài viết của người dùng
<p></p>
<img src="img/Screenshot 2023-11-19 092508.png"/>
<img src="img/Screenshot 2023-11-19 092546.png"/>
6. Chức năng hiển thị chi tiết bài viết
<p></p>
- ✍ Hiển thị các thông tin của bài viết như tiêu đề, thời gian đăng, tác giả, nội dung, danh sách bài viết liên quan, danh sách bình luận, ...
<p></p>
- ✍ Có thể thêm bình luận, trả lời bình luận và sửa, xóa bình luận (Chỉ những bình luận của mình mới cho phép sửa, xóa)
<p></p>
<img src="img/Screenshot 2023-11-19 092625.png"/>
<img src="img/Screenshot 2023-11-19 092647.png"/>
- ✍ Khi chọn trả lời bình luận sẽ hiển thị form điền thông tin bình luận
<p></p>
<img src="img/Screenshot 2023-11-19 092752.png"/>
<p></p>
<img src="img/Screenshot 2023-11-19 092839.png"/>
- ✍ Khi chọn gửi bình luận sẽ dẫn tới trang danh sách bình luận con (bình luận đã trả lời) của bình luận mà mình vừa trả lời
<p></p>
<img src="img/Screenshot 2023-11-19 092859.png"/>
- ✍ Đồng thời khi chúng ta quay lại danh sách bình luận của bài viết thì bình luận vừa trả lời cũng xuất hiện thêm chức năng xem thêm bình luận
<p></p>
<img src="img/Screenshot 2023-11-19 092921.png"/>
- ✍ Khi chọn sửa bình luận bất kì sẽ hiển thị form chứa các thông tin của bình luận đó
<p></p>
- ✍ Nếu chọn trạng thái bình luận là public thì tất cả mọi người sẽ nhìn thấy được bình luận đó, còn private thì tất cả mọi người sẽ không nhìn thấy được (trừ người viết bình luận)
<p></p>
<img src="img/Screenshot 2023-11-19 093003.png"/>
- ✍ Khi chọn sửa bình luận sẽ quay lại trang bài viết đó
<p></p>
<img src="img/Screenshot 2023-11-19 093133.png"/>
- ✍ Chọn xóa để xóa bình luận
<p></p>
<img src="img/Screenshot 2023-11-19 093221.png"/>
- ✍ Khi click vào một bài viết liên quan bất kì sẽ dẫn đến trang bài viết đó
<p></p>
<img src="img/Screenshot 2023-11-19 093309.png"/>
7. Chức năng sửa bài viết
<p></p>
- ✍ Hiển thị form chứa các thông tin cho phép sửa của bài viết 
<p></p>
- ✍ Nếu chọn trạng thái bài viết là public thì ở trang All Posts tất cả mọi người sẽ nhìn thấy bài viết đó, còn private thì ở trang All Posts tất cả mọi người sẽ không nhìn thấy được (trừ tác giả của bài viết)
<p></p>
<img src="img/Screenshot 2023-11-19 093548.png"/>
<img src="img/Screenshot 2023-11-19 093605.png"/>
- ✍ Sau khi nhấn chọn Update sẽ sửa bài viết và quay lại trang My Posts
<p></p>
<img src="img/Screenshot 2023-11-19 093645.png"/>
8. Chức năng xóa bài viết
<p></p>
- ✍ Chọn xóa cạnh tên bài viết muốn xóa để xóa bài viết đó
<p></p>
<img src="img/Screenshot 2023-11-19 093645.png"/>
9. Chức năng thêm bài viết liên quan
<p></p>
- ✍ Chọn thêm bài viết liên quan cho bài viết muốn thêm thì sẽ hiển thị form yêu cầu nhập các thông tin cần thiết
<p></p>
<img src="img/Screenshot 2023-11-19 093710.png"/>
- ✍ Sau khi nhập đầy đủ thông tin và chọn Post thì bài viết sẽ được thêm và hiển thị trang danh sách bài viết liên quan của bài viết ban đầu
<p></p>
<img src="img/Screenshot 2023-11-19 094016.png"/>
10. Chức năng xem bài viết liên quan
<p></p>
- ✍ Chọn xem bài viết liên quan thì cũng sẽ hiển thị trang danh sách bài viết liên quan như khi chúng ta thêm thành công
<p></p>
<img src="img/Screenshot 2023-11-19 093645.png"/>
<img src="img/Screenshot 2023-11-19 094016.png"/>
11. Hiển thị tất cả bài viết của tất cả người dùng
<p></p>
- ✍ Chỉ hiển thị những bài viết có trạng thái là Public. Còn những bài viết Private thì chỉ hiển thị đối với tác giả
<p></p>
<img src="img/Screenshot 2023-11-19 094151.png"/>
<img src="img/Screenshot 2023-11-19 094207.png"/>
