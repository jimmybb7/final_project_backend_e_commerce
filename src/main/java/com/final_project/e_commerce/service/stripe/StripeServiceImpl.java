package com.final_project.e_commerce.service.stripe;

import com.final_project.e_commerce.config.EnvConfig;
import com.final_project.e_commerce.data.domainData.responseDomainData.transaction.ResponseTransactionDomain;
import com.final_project.e_commerce.data.entity.transaction.TransactionEntity;
import com.final_project.e_commerce.data.entity.transactionProduct.TransactionProductEntity;
import com.final_project.e_commerce.mapper.transaction.ChangeToDomainTransaction;
import com.final_project.e_commerce.service.transactionProduct.TransactionProductService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StripeServiceImpl implements StripeService {

    private final TransactionProductService transactionProductService;
    private final ChangeToDomainTransaction changeToDomainTransaction;

    public StripeServiceImpl(TransactionProductService transactionProductService, ChangeToDomainTransaction changeToDomainTransaction) {
        this.transactionProductService = transactionProductService;
        this.changeToDomainTransaction = changeToDomainTransaction;
    }

    @Override
    public String creatStripePaymentSession(TransactionEntity transactionEntity) throws StripeException {
        Stripe.apiKey = "sk_test_51Sh2rmRTJa0i8snxYC9LkrxS7tfxUpMAvqhw2f3IefkXHhtrae3euxbpYLwpAL14Ig2zeAOF8DEF29YAIEOEi4x600u4gQzcgg";
        String YOUR_DOMAIN = EnvConfig.PROD_BASEURL;

        List<TransactionProductEntity> transactionProductListByTid = transactionProductService.getTransactionProductByTid(transactionEntity.getTid());
        ResponseTransactionDomain responseTransactionDomain = changeToDomainTransaction.transactionEntityChangeToResponseTransactionDomain(transactionEntity, transactionProductListByTid);
        List<SessionCreateParams.LineItem> stripeItemList = new ArrayList<>();


        responseTransactionDomain.getProducts().forEach(transactionProduct -> {
            SessionCreateParams.LineItem stripeItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(Long.valueOf(transactionProduct.getQuantity()))
                    .setPrice(transactionProduct.getProduct().getStripePriceId())
                    .build();
            stripeItemList.add(stripeItem);
        });


        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(YOUR_DOMAIN + "/paymentLoading/" + transactionEntity.getTid())
                        .setCancelUrl(YOUR_DOMAIN + "/")
                        .addAllLineItem(stripeItemList)
                        .build();
        Session session = Session.create(params);

        transactionEntity.setStripeSessionId(session.getId());

        return session.getUrl();
    }


    @Override
    public boolean isPaymentCompleted(TransactionEntity transactionEntity) throws StripeException {
        Stripe.apiKey = "sk_test_51Sh2rmRTJa0i8snxYC9LkrxS7tfxUpMAvqhw2f3IefkXHhtrae3euxbpYLwpAL14Ig2zeAOF8DEF29YAIEOEi4x600u4gQzcgg";
        Session session = Session.retrieve(transactionEntity.getStripeSessionId());
        return "paid".equals(session.getPaymentStatus());
    }
}
