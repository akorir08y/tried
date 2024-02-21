package com.example.tried.services;


import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.ListMembersResponse;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembers;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembersResponse;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccounts;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccountsResponse;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.financial.MemberOffering;
import com.example.tried.auth.financial.MemberOfferingResponse;
import com.example.tried.auth.member.RequestChurchDetails;
import com.example.tried.auth.member.RequestChurchDetailsResponse;
import com.example.tried.auth.member.RequestChurchDetailsWithCode;
import com.example.tried.auth.member.RequestChurchDetailsWithCodeResponse;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelReset;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.PersonnelResetResponse;

public interface AuthApi {

    // Login as a Member Credentials
    AuthMemberResponse getMemberCredentials(LoginCredentials credentials);

    // Verify Phone Number Using OTP
    public SMSResponse VerifyPhoneNumber(String recipient);

    // Reset Member Pin
    AuthMemberResetResponse resetMemberPin(Payload payload);

    // Member Registration
    AuthMemberRegistrationResponse registerMember(AuthMemberRegister register);

    // Member Registration Update
    AuthMemberRegistrationResponse updateRegisterMember(AuthMemberRegister register);

    // Member Personnel Login
    MemberPersonnelResponse loginMemberPersonnel(MemberPersonnel personnel);

    // Member Personnel Password Reset
    PersonnelResetResponse resetPersonnelPassword(MemberPersonnelReset reset);

    // Valid OTP
    // Member Personnel Password Reset
    public String validateOTPPassword(OtpValidationRequest otpValidationRequest);

    public int getOTPPin();


    // Fetch Member Details
    public MemberProfileResponse getMemberDetails(MemberProfile profile);

    // Get the Member Statement
    public MemberOfferingResponse getMemberOffering(MemberOffering offering);

    // Get the Member Church Details
    public RequestChurchDetailsResponse getMemberChurchDetails(RequestChurchDetails request);

    // Get the Member Transfer Details
    public MemberTransferResponse getMemberTransfer(MemberTransfer transfer);

    // Request Church Details with Church Code
    public RequestChurchDetailsWithCodeResponse getChurchCodeDetails(RequestChurchDetailsWithCode requestChurchDetails);


    // Member Registration Update Details
    public MemberRegisterUpdateResponse getMemberRegistrationUpdate(MemberRegistrationUpdate registrationUpdate);


    // List Church Members
    public ListMembersResponse getChurchMembers(ListMembers members);

    // List Deactivated Church Members
    public ListDeactivatedMembersResponse getDeactivatedMembers(ListDeactivatedMembers members);

    // List Payment Accounts
    public LocalChurchPaymentAccountsResponse getPaymentAccounts(LocalChurchPaymentAccounts paymentAccount);

    // Get the Local Church Trust Fund Summary
    public LocalChurchTrustFundSummaryResponse getLocalChurchTrustFundSummary(LocalChurchTrustFundSummary trustFundSummary);
}
