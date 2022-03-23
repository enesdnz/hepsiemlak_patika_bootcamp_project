
# <img src="https://github.com/enesdnz/hepsiemlak_patika_bootcamp_project/blob/master/screenshot/logo.png" width="60"/> Hepsiemlak - Patika - Bootcamp

Bu proje Patika.dev' in aracılığıyla düzenlenen Hepsiemlak Java Spring Bootcamp bitirme ödevidir.

Hepsiemlak uygulamasının küçük çaplı geliştirmesidir.

Proje kapsamında kullanıcı kayıt olabilir, ilanlar oluşturabilir, oluşturduğu ilanların
statülerini değiştirebilir ve ilan paylaşmak amacıyla oluşturulan ürünü satın alabilir.



## Microservice Mimarisi

![microservice_uml](https://github.com/enesdnz/hepsiemlak_project/blob/master/screenshot/uml.png)

  
## API Kullanımı

#### discovery-server
###
```http
  path : localhost:5858
```

![discovery_server](https://github.com/enesdnz/hepsiemlak_project/blob/master/screenshot/eureka-server.png)

#### auth-service
###
```http
  path : localhost:5860/auth
```

| Parametre | Erişim Noktası     | Açıklama                |
| :-------- | :------- | :------------------------- |
| POST | `auth/` | Email ve şifresi verilen kullanıcı için token oluşturur. |


#### common-service
###

#### user
```http
  path : localhost:5860/user
```

| Parametre | Erişim Noktası     | Açıklama                |
| :-------- | :------- | :------------------------- |
| GET | `user/` | Veritabanındaki bütün kullanıcıları getirir. |
| GET | `user/userId` | Veritabanında verilen id değeri ile eşleşen kullanıcıyı getirir. |
| POST | `user/` |   İstek atan kullanıcıyı veritabanına kaydeder. |
| PUT | `user/userId` |   Id si verilen kullanıcı için güncelleme işlemi gerçekleştirir. |
| DELETE | `user/userId` |   Id si verilen kullanıcı için silme işlemi gerçekleştirir. |

#### advert
```http
  path : localhost:5860/advert
```

| Parametre | Erişim Noktası     | Açıklama                |
| :-------- | :------- | :------------------------- |
| GET | `advert/` | Veritabanındaki bütün ilanları getirir. |
| GET | `advert/advertNo` | Verilen ilan numarasına göre ilanını getirir. |
| GET | `advert/advertStatus` | Statuye göre ilanları getirir. (ACTIVE ve PASSIVE) |
| POST | `advert/` |  Validasyon işlemleri sonrası ilanı kaydeder. |
| PUT | `advert/advertNo` |  İlan numarası verilen ilan için güncelleme işlemi gerçekleştirir. |
| DELETE | `advert/advertNo` |   İlan numarası verilen ilan için silme işlemi gerçekleştirir. |

#### product
```http
  path : localhost:5860/product
```

| Parametre | Erişim Noktası     | Açıklama                |
| :-------- | :------- | :------------------------- |
| GET | `product/userId` | Id si verilen kullanıcı ürün satın almış ise sonucu döndürür. |
| POST | `product/purchase` | Kullanıcının ürün satın almasını sağlar. |

  
## Postman Sorguları

Postman tarafından atılan sorguların ekran görüntülerine [buradan](https://github.com/enesdnz/hepsiemlak_project/tree/master/postman-requests) ulaşabilirsiniz.

## Log Kayıtları

Sistem tarafından tutulan log kayıtlarına ilgili servis altındaki **logback.log** dosyasından ulaşabilirsiniz.
  
## Unit Test Code Coverage

Yazılan unit testlerin code coverage oranlarına [buradan](https://github.com/enesdnz/hepsiemlak_patika_bootcamp_project/tree/master/unit_test_coverage_rate) ulaşabilirsiniz. 
  
## Kullanılan Teknolojiler

- Java 11
- JUnit 5
- Spring Boot
- Microservice Mimarisi
- Mysql 
- RabbitMQ
- Hibernate

  
## Neler Yapılabilir ? 

- Frontend eklenmesi gerekiyor.

- Docker eklenebilir.

- Uygulama kapsamı genişletilebilir.

  
## İletişim

- Muhammet Enes Deniz [Linkedin](https://www.linkedin.com/in/muhammetenesdeniz/)
- [Github ](https://github.com/enesdnz)

## LICENSE

Distributed under the MIT License. See [LICENSE](https://github.com/enesdnz/hepsiemlak_patika_bootcamp_project/blob/master/LICENSE) for more information.

  
 
