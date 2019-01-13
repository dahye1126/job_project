var join = join || {};

join.account = {
    type: '',
    eduId: '', // 교육회원 아이디
    only_gg: function () {
        this.type = 'gg';
        // SNS 회원 가입
        if (Social) {
            Social.naver("#btnNvLogin", "/Naver/Callback");
            Social.google("#btnGlLogin");
            Social.kakao("#btnKaLogin");
        }
        // 인증번호 발송
        $('#btnSendCert, #btnReSendCert').click(this.send_cert_num);
        // 인증번호 확인
        $('#btnCheckCert').click(this.check_cert);
        // 인증번호 입력
        $('#Certify_Num').only_num();
        $('#frm').submit(this.submit);
    },
    init: function (type) {
        this.type = type;
        this.eduId = $('#hdnEduId').val();
        // 이름
        $('.dev-name').blur({ selector: '.dev-name'}, join.common.check_empty);
        // 아이디
        $('.dev-id').blur({ id: '.dev-id'}, this.check_unique_id)
                    .keyup(this.check_edu_id);
        // 비밀번호
        $('.dev-password').keyup(this.check_password).blur(this.check_password);

        // 핸드폰
        $('.dev-phone').only_num()
                       .blur(this.check_phone)
                       .keyup(this.keyup_phone);
        // 이메일
        $('.dev-mail').blur(this.check_email);        
    },    
    submit: function (e) {
        var c = join.common;
        //ReadOnlyDiabled(false);
        
        //약관 동의
        join.account.check_agree();
        //이름
        c.check_empty('.dev-name');
        //아이디 체크
        join.account.check_unique_id('.dev-id');
        //비밀번호
        join.account.check_password();        
        //이메일
        join.account.check_email();
        //휴대전화
        join.account.check_phone();
        //파견도급
        join.account.check_CoDG();

        // 개인회원 용
        if (join.account.type === 'gg') {
            $(".dev-id").val($("#idcheck").val());

            //인증 완료 확인
            if ($("#CertifyCode").val().length == 0) {

                if (c.get_dev_mode() === 'dev' || c.get_dev_mode() === 'jts') {
                    // test 용 코드
                    c.toggle_msg('.authentication_number', '', false, 'success');
                } else {
                    if ($("#ReSubmit").val().length > 0) {
                        c.toggle_msg('.authentication_number', "인증번호 입력 후 확인버튼을 클릭해 주세요.");
                    }
                    else {
                        c.toggle_msg('.authentication_number', "휴대폰 인증이 필요합니다.");
                    }
                }                
            }
        }

        if ($(".failure").length > 0) {
            var $fail = $('.failure').eq(0);
            var $target = $fail.siblings('input').length > 0 ? $fail.siblings('input') : $fail;
            $(document).scrollTop($target.offset().top);
            $target.focus();
            return false;
        }
        else {
            return true;
        }
    },
    check_phone: function () {
        $('#Corp_Phone_Etc').val('');
        var phone = $('.dev-phone').val();
        var isPhone = false;
        if (phone.length == 0) {
            join.common.toggle_msg('.dev-phone', join.msg.required);
        } else if (join.account.check_foreign()) {
            $('#Corp_Phone1').val('000');
            $('#Corp_Phone2').val('0000');
            $('#Corp_Phone3').val('0000');
            $('#Corp_Phone_Etc').val(phone);

            if ($.inArray('', phone.split(' ')) > -1) {
                join.common.toggle_msg('.dev-phone', '띄어쓰기를 연속으로 사용할 수 없습니다.');
            } else if (isNaN(phone.replace(/ /gi, ''))) {
                join.common.toggle_msg('.dev-phone', '올바르지 않은 전화번호 입니다.');
            } else {
                join.common.toggle_msg('.dev-phone', '', false, 'success');
            }
            return false;
        } else if (phone.length > 2) {
            var regExp = '', msg = '';
            if (join.account.type === 'gg') {
                regExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
                msg = '올바르지 않은 휴대폰 번호입니다';
                isPhone = regExp.test(phone);
            } else {
                var firstPhone = '02|031|032|033|041|042|043|044|051|052|053|054|055|061|062|063|064|070|010|011|016|017|018|019'.split('|');
                for (var index in firstPhone) {
                    if (phone.substring(0, 3).indexOf(firstPhone[index]) > -1) {
                        isPhone = true;
                        break;
                    }
                }

                if (isPhone) {
                    regExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
                    isPhone = regExp.test(phone);
                }
                msg = '올바르지 않은 전화번호입니다';
            }
            
            if (isPhone) {
                var arr = phone.split('-');
                if (arr.length > 2) {
                    var code = join.account.type === 'gg' ? 'M' : 'Corp';
                    $('#' + code + '_Phone1').val(arr[0]);
                    $('#' + code + '_Phone2').val(arr[1]);
                    $('#' + code + '_Phone3').val(arr[2]);
                }
                join.common.toggle_msg('.dev-phone', '', false, 'success');
            } else {
                join.common.toggle_msg('.dev-phone', msg);
            }            
        }
        return isPhone;
    },    
    check_foreign: function(){
        return typeof join.corp !== 'undefined' && join.corp.check_foreign();
    },
    change_foreign: function () {
        var $phone = $('.dev-phone');
        if ($phone.length === 1) {
            if (join.account.check_foreign()) {
                // 핸드폰
                $phone.val('')
                $phone.unbind('keydown').attr('maxlength', 30);
                join.common.toggle_msg('.dev-phone', '', false, 'success');
            } else {
                if ($phone.val().length > 13) {
                    $phone.val('')                    
                }
                $phone.only_num().attr('maxlength', 13);
            }
            join.common.init_zoom();
        }
    },
    keyup_phone: function (e) {
        if ($(this).length > 0 && !join.account.check_foreign()) {
            var $phone = $(this);
            var phone = $phone.val();
            var origin = phone;
            var len = 0, n1 = 3, n2 = 1;
            phone = phone.replace(/-/gi, '');
            len = phone.length;
            
            if (len > 2 && phone.substring(0, 2) === '02') {
                n1 = 2;
                n2 = 2;
            }

            if (len > n1) {
                phone = phone.substring(0, n1) + '-' + phone.substring(n1, len);
                len++;
            }
            if (len > (n1 + 4)) {
                phone = phone.substring(0, len - (n1 + n2)) + '-' + phone.substring(len - (n1 + n2), len);
                len++;
            }
            if (len > (n1 + 10)) {
                phone = phone.substring(len - 10, len);
            }            
            
            if (origin !== phone) $phone.val(phone);
        }
    },
    check_CoDG: function () {
        if (join.account.type === 'gi') {
            var $cagree = $('input[name=CoDG_Agree]');
            if ($cagree.length === 1) {
                $cagree.val($cagree.is(':checked'));
            }
        }
    },
    check_email: function () {
        // var Default_Text = ''; //<p class=\"info\">개인 맞춤 채용정보/정기 뉴스레터/이벤트 메일이 발송됩니다.</p>";

        var email = $('.dev-mail').val();

        if (email.length == 0) {
            join.common.toggle_msg('.dev-mail', join.msg.required);
            // $("#notice_msg_mail").html($("#notice_msg_mail").html() + Default_Text);
        } else {
            if (join.account.check_email_reg(email) == false) {
                join.common.toggle_msg('.dev-mail', join.msg.email.unselected);
            } else {
                var arr = email.split('@');
                if (arr.length > 1) {
                    $('#Email_ID').val(arr[0]);
                    $('#Email_Addr').val(arr[1]);
                }
                join.common.toggle_msg('.dev-mail', '', false, 'success');
            }
        }

        if (join.account.type === 'gg') {
            var $agree = $('input[name=M_Email_Agree]');
            if ($agree.length === 1) {
                $agree.val($agree.is(':checked') ? 1 : 0);
            }
        } else if (join.account.type === 'gi') {
            var $agree = $('input[name=Email_Agree]');
            if ($agree.length === 1) {
                $agree.val($agree.is(':checked'));
            }
        }
    },
    //이메일 올바른 형식 검사
    check_email_reg: function (email) {
        var email = escape(email);
        var regExp = /^[a-z A-Z 0-9\w-\.]+@[a-z A-Z 0-9\-]+(\.[a-z A-Z 0-9 \-]+)+$/;
        if (email.match(regExp) == null) {
            return false;
        }
        else {
            return true;
        }
    },
    //약관 동의
    check_agree: function () {
        if (!$("input:checkbox[id='lb_chk_service']").prop('checked') || !$("input:checkbox[id='lb_chk_privacy']").prop('checked')) {
            join.common.toggle_msg('#notice_msg_agree', join.msg.agree);
            alert('잡코리아 회원약관과 개인정보 수집 및 이용에 대한 안내를 모두 동의해주세요.');
        }
        else {
            join.common.toggle_msg('#notice_msg_agree', '', false, 'success');
        }
    },
    //아이디 문자열 형식 체크
    check_id: function (id) {
        if (_.isUndefined(id)) {
            id = arguments[0].data.id;
        }

        id = id || $(this).val();
        var err_cnt = 0
        for (var i = 0; i < id.length; i++) {
            var val = id.charAt(i);
            if (!((val >= "0" && val <= "9") || (val >= "a" && val <= "z"))) // || (val >= "A" && val <= "Z")))
                err_cnt++
        }
        if (err_cnt == 0) return true;
        else return false;
    },
    //아이디 중복 체크
    check_unique_id: function (selector) {
        var c = join.common, m = join.msg;
        if (_.isUndefined(selector) || typeof selector === 'object') {
            selector = arguments[0].data.id;
        }
        selector = selector || $(this).val();
        var $obj = $(selector);
        var eduId = join.account.eduId;

        if ($obj.val().replace(eduId, '').length == 0) {
            c.toggle_msg(selector, m.required);
        } else if ($obj.val().length < 6 || $obj.val().length > 16 || !join.account.check_id($obj.val().replace(eduId, ''))) {
            c.toggle_msg(selector, m.id.incongruent);
        } else {
            $.ajax({
                url: "/Join/IDCheck",
                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                type: "POST",
                dataType: "json",
                data: "testid=" + $obj.val(),
                error: function (data) {
                    alert(data.status);
                    alert(data.responseText);
                    return false;
                },
                success: function (data) {
                    if (data.ReturnInt > 0) {
                        c.toggle_msg(selector, m.id.overlap);
                    }
                    else {
                        if ($('input[name=M_Id]').length === 1) {
                            $('input[name=M_Id]').val($obj.val());
                        }
                        c.toggle_msg(selector, m.id.use, true, 'success', 2);
                    }
                }
            });
        }
        return false;
    },
    //교육기관 회원 아이디 체크
    check_edu_id : function (e) {
        var id = $(this).val();

        var eduId = join.account.eduId;

        if(!_.isUndefined(eduId)){
            if (id.indexOf(eduId) < 0 && eduId.length > 0) {
                join.common.toggle_msg('.dev-id', "교육기관을 통한 가입은 '" + eduId + "'가 부여되어 가입이 진행됩니다.");
                $("#idcheck").val(eduId).focus();
            }
        }
    },
    // 인증번호 발송
    send_cert_num: function () {
        var selector = '.dev-phone';
        if ($('#DI_Code').val().split(' ').join('') != '') {
            join.common.toggle_msg(selector, '아이핀 인증되었습니다.', true, 'success');
            return false;
        }
        else {
            if ($('input:radio[name="lb_certifytype"][value="3"]').prop('checked')) {
                join.common.toggle_msg(selector, '아이핀 인증 요청 중입니다.');
            }
            else {
                var mobile = $(selector).val();

                if (mobile.split(' ').join('') == '') {
                    join.common.toggle_msg(selector, '휴대폰번호를 입력해주세요.');
                    return false;
                }

                if (!join.account.check_phone()) {
                    return false;
                }

                var CertifyNum = $('#Certify_Num').val();

                //var strParam = 'UserName=' + escape(UserName) + '&UserBirth=' + UserBirth + '&UserGender=' + UserGender + '&UserMobile=' + UserMobile + '&CertifyNum=' + CertifyNum + '&Mem_Type=' + Mem_Type + '&OEM_No=' + Oem_No + '&CertifytySvc=' + CertifytySvc + '&Certifytype=2';
                var strParam = 'UserMobile=' + mobile + '&CertifyNum=' + CertifyNum + '&Mem_Type=' + Mem_Type + '&OEM_No=' + Oem_No + '&CertifytySvc=' + CertifytySvc + '&Certifytype=2';

                //인증여부 초기화
                $('#CertifyCode').val('');

                if ($('#getCertifynum').val() != '1' || $('#ReSubmit').val() == '1') { //subType :1 (인증번호 재전송)
                    $.ajax({
                        url: '/Certify/PhoneEmailCertifyNumCall',
                        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                        type: 'POST',
                        dataType: 'json',
                        data: strParam,
                        cache: false,
                        success: function (data) {
                            if (data.ReturnCode == 2) {  //휴대폰

                                $('#Certify_Num').show();
                                $('.btnConfirm').show();

                                if ($('#ReSubmit').val() == '1') {
                                    join.common.toggle_msg(selector, '인증번호가 재전송되었습니다.', true, 'success', 2);
                                    join.common.toggle_msg('#Certify_Num', '', false);
                                } else {
                                    join.common.toggle_msg(selector, '인증번호가 전송되었습니다.', true, 'success', 2);
                                }

                                $('#getCertifynum').val('1');
                                $('#ReSubmit').val('1');

                                $('#Certify_Num').val('');
                                $('#Certify_Num').focus();


                            } else if (data.ReturnCode == 20) { //휴대폰 인증 1일 5회 이상인 경우
                                join.common.toggle_msg('.dev-phone', '휴대폰인증은 1일 5회까지만 가능합니다.');
                                return false;
                            } else if (data.ReturnCode == 0) { //조건에 맞는 아이디 존재시 아이디 리스트로 이동

                            }

                            //인증번호 입력 비활성화
                            $('#Certify_Num').prop('disabled', false);
                        }
                    });
                }
            }
        }
    },
    //인증번호 확인
    check_cert: function () {
        var selector = '#Certify_Num';
        
        if ($('#CertifyCode').val().split(' ').join('') != '') {
            join.common.toggle_msg(selector, '휴대폰 인증되었습니다.', true, 'success');
            return false;
        }
        else {            
            var UserMobile = $('.dev-phone').val();
            var CertifyNum = $('#Certify_Num').val();
            //var strParam = 'UserName=' + escape(UserName) + '&UserBirth=' + UserBirth + '&UserGender=' + UserGender + '&UserMobile=' + UserMobile + '&CertifyNum=' + CertifyNum + '&Mem_Type=' + Mem_Type + '&OEM_No=' + Oem_No + '&CertifytySvc=' + CertifytySvc + '&Certifytype=2';
            var strParam = 'UserMobile=' + UserMobile + '&CertifyNum=' + CertifyNum + '&Mem_Type=' + Mem_Type + '&OEM_No=' + Oem_No + '&CertifytySvc=' + CertifytySvc + '&Certifytype=2';
            //인증여부 초기화
            $('#CertifyCode').val('');

            if ($('#Certify_Num').val() == '' || $('#Certify_Num').val().length < 6) {
                join.common.toggle_msg(selector, '인증번호 입력 후 확인버튼을 클릭해 주세요.');
                $('#Certify_Num').val('');
                $('#Certify_Num').focus();
            } else {
                jQuery.ajax({
                    url: '/Certify/PhoneEmailCertifyResult',
                    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
                    type: 'POST',
                    dataType: 'json',
                    data: strParam,
                    cache: false,
                    success: function (data) {
                        if (data.ReturnCode == 1) {
                            join.common.toggle_msg(selector, '인증되었습니다.', true, 'success');
                            $('#CertifyCode').val(data.CertifyCode);                            
                        } else {
                            join.common.toggle_msg(selector, '인증번호가 맞지 않습니다. 다시 확인해 주세요.');
                            $('#Certify_Num').val('');
                            $('#Certify_Num').focus();
                        }
                    }
                });
            }
        }
    },
    check_password: function () {
        var ja = join.account;
        var level = 0;
        var pwd = $('.dev-password').val();
        var id = $('.dev-id').val();

        if (pwd.length == 0) {
            level = 100;
        } else if (ja.is_hangeul(pwd)) {
            level = 110;
        } else if (pwd.length < 6) {
            level = 101;	// 6자리미만
        } else if (pwd == id) {
            level = 102;	// 아이디동일
        } else if (ja.is_single(pwd)) {
            level = 103;	// 한문자/숫자 사용	
        } else if (ja.is_continued(pwd, pwd.length)) {
            level = 104;	// 자릿수 상관없이 연속된 문자,숫자
        } else {
            var ret = ja.is_mix(pwd);
            if (ret < 2) {
                level = 105;    // 숫자,문자,특수문자 조합 한가지				 
            } else if (ja.is_same(pwd, 3)) {
                level = 201;	// 3자리 이상 동일한 문자,숫자
            } else if (ja.is_continued(pwd, 3)) {
                level = 202;	// 3자리 이상 연속된 문자,숫자			
            } else if (ja.is_pattern(pwd)) {
                level = 204;	// 	키보드 배열패턴 사용
            } else if (id != null && pwd.indexOf(id) >= 0) {
                level = 213;	// 아이디 포함			
            } else if (ret == 2) {
                level = 300;	// 숫자,문자,특수문자 조합 두가지
            } else if (ret >= 3) {
                level = 400;	// 숫자,문자,특수문자 조합 세가지 이상
            } else {
                level = 200;    // 알 수 없음 => 낮음으로 ~ 
            }
        }
        ja.msg_pwd(level);
    },
    msg_pwd: function(level) {   
        if (level == 100) {
            join.common.toggle_msg('#notice_msg_pwd', '필수 정보입니다.')
        } else if (level == 110) {
            join.common.toggle_msg('#notice_msg_pwd', '사용불가 (6~16자의 영문,숫자,특수기호)')
        } else if (level > 100 && level <= 105) {
            join.common.toggle_msg('#notice_msg_pwd', '사용불가 (안전도 등급 매우 약함)')
        } else {
            var className = "", message = "";
            switch (parseInt(String(level).substr(0, 1))) {
                case 2:
                    message = "사용가능 (안전도 등급 낮음)";
                    className = "success";
                    break;
                case 3:
                    message = "사용가능 (안전도 등급 적정)";
                    className = "success";
                    break;
                case 4:
                    message = "사용가능 (안전도 등급 높음)";
                    className = "success";
                    break;
                case 1:
                    message = "사용불가 (안전도 등급 매우 약함)";
                    className = "failure";
                    break;
                default:

            }
            join.common.toggle_msg('#notice_msg_pwd', message, true, className, className === 'success' ? 2 : undefined)
        }
        document.getElementById("notice_msg_pwd").style.display = 'block';
    },
    is_single: function (text) {
        var cnt = 1;
        for (var i = 0; i <= text.length; i++) {
            if (text.charAt(0) == text.charAt(i + 1)) {
                cnt++;
            }
        }
        if (text.length == cnt) {
            return true;
        } else {
            return false;
        }
    },
    is_same: function (text, cnt) {
        var ret = false;
        var checkStr = ""; // 같은 반복문자 체크(예: aaaa)

        for (var z = 1; z < cnt; z++) {
            checkStr += "text.charAt(i) == text.charAt(i + " + z + ")";
            if (z < cnt - 1) {
                checkStr += " && ";
            }
        }

        for (var i = 0; i < text.length; i++) {
            if (eval(checkStr)) {
                ret = true;
                break;
            }
        }
        return ret;
    },
    is_continued: function (text, cnt) {
        var ret = false;
        var checkAsc = ""; // 연속된 오름차순 숫자 혹은 문자(예: abcde)
        var checkDesc = ""; // 연속된 내림차순 숫자 혹은 문자(예: edcba)

        for (var z = 1; z < cnt; z++) {
            checkAsc += "(text.charCodeAt(i) + " + z + ") == text.charCodeAt(i + " + z + ")";
            checkDesc += "(text.charCodeAt(i) - " + z + ") == text.charCodeAt(i + " + z + ")";
            if (z < cnt - 1) {
                checkAsc += " && ";
                checkDesc += " && ";
            }
        }

        for (var i = 0; i < text.length; i++) {
            if (eval(checkAsc) || eval(checkDesc)) {
                ret = true;
                break;
            }
        }

        return ret;
    },
    is_mix: function (text) {
        var ret = 0;
        var expNum = /^[0-9]$/;
        var expStr = /^[a-z]$/;
        var expStr2 = /^[A-Z]$/;

        var arrString = new Array();
        var cntString = 0;
        var cntString2 = 0;
        var cntNumber = 0;
        var cntEtc = 0;

        for (var i = 0; i < text.length; i++) {
            arrString[i] = text.substring(i, i + 1);

            if (expNum.test(arrString[i])) {
                cntNumber++;
            } else if (expStr.test(arrString[i])) {
                cntString++;
            } else if (expStr2.test(arrString[i])) {
                cntString2++;
            } else {
                cntEtc++;
            }
        }
        //alert("cntNumber : " + cntNumber + " , cntString : " + cntString + " , cntString2 : " + cntString2 + " , cntEtc : " + cntEtc);
        if (cntString > 0) { ret++; }
        if (cntString2 > 0) { ret++; }
        if (cntNumber > 0) { ret++; }
        if (cntEtc > 0) { ret++; }

        return ret;
    },
    is_hangeul: function(text) {
        var chk = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if(chk.test(text)) {
            return true;
        } else {
            return false;
        }
    },
    is_pattern: function(text) {
        var pt1 = "asdfghjkl";
        var pt2 = "lkjhgfdsa";
        var pt3 = "qwertyuiop";
        var pt4 = "poiuytrewq";
        var pt5 = "zxcvbnm";
        var pt6 = "mnbvcxz";

        if (pt1.indexOf(text) >= 0 || pt2.indexOf(text) >= 0 || pt3.indexOf(text) >= 0 || pt4.indexOf(text) >= 0 || pt5.indexOf(text) >= 0 || pt6.indexOf(text) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}