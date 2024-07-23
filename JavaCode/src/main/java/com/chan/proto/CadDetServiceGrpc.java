package com.chan.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.64.0)",
    comments = "Source: cadDet.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CadDetServiceGrpc {

  private CadDetServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "CadDetProto.CadDetService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.chan.proto.CadDetProto.CadDetRequest,
      com.chan.proto.CadDetProto.CadDetResponse> getExecuteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Execute",
      requestType = com.chan.proto.CadDetProto.CadDetRequest.class,
      responseType = com.chan.proto.CadDetProto.CadDetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.chan.proto.CadDetProto.CadDetRequest,
      com.chan.proto.CadDetProto.CadDetResponse> getExecuteMethod() {
    io.grpc.MethodDescriptor<com.chan.proto.CadDetProto.CadDetRequest, com.chan.proto.CadDetProto.CadDetResponse> getExecuteMethod;
    if ((getExecuteMethod = CadDetServiceGrpc.getExecuteMethod) == null) {
      synchronized (CadDetServiceGrpc.class) {
        if ((getExecuteMethod = CadDetServiceGrpc.getExecuteMethod) == null) {
          CadDetServiceGrpc.getExecuteMethod = getExecuteMethod =
              io.grpc.MethodDescriptor.<com.chan.proto.CadDetProto.CadDetRequest, com.chan.proto.CadDetProto.CadDetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Execute"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chan.proto.CadDetProto.CadDetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.chan.proto.CadDetProto.CadDetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CadDetServiceMethodDescriptorSupplier("Execute"))
              .build();
        }
      }
    }
    return getExecuteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CadDetServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CadDetServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CadDetServiceStub>() {
        @java.lang.Override
        public CadDetServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CadDetServiceStub(channel, callOptions);
        }
      };
    return CadDetServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CadDetServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CadDetServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CadDetServiceBlockingStub>() {
        @java.lang.Override
        public CadDetServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CadDetServiceBlockingStub(channel, callOptions);
        }
      };
    return CadDetServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CadDetServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CadDetServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CadDetServiceFutureStub>() {
        @java.lang.Override
        public CadDetServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CadDetServiceFutureStub(channel, callOptions);
        }
      };
    return CadDetServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void execute(com.chan.proto.CadDetProto.CadDetRequest request,
        io.grpc.stub.StreamObserver<com.chan.proto.CadDetProto.CadDetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExecuteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CadDetService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static abstract class CadDetServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CadDetServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CadDetService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CadDetServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CadDetServiceStub> {
    private CadDetServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CadDetServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CadDetServiceStub(channel, callOptions);
    }

    /**
     */
    public void execute(com.chan.proto.CadDetProto.CadDetRequest request,
        io.grpc.stub.StreamObserver<com.chan.proto.CadDetProto.CadDetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CadDetService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CadDetServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CadDetServiceBlockingStub> {
    private CadDetServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CadDetServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CadDetServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.chan.proto.CadDetProto.CadDetResponse execute(com.chan.proto.CadDetProto.CadDetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExecuteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CadDetService.
   * <pre>
   *&#64; 7 定义一个服务来描述要生成的API接口，类似于Java的业务逻辑接口类
   * </pre>
   */
  public static final class CadDetServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CadDetServiceFutureStub> {
    private CadDetServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CadDetServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CadDetServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.chan.proto.CadDetProto.CadDetResponse> execute(
        com.chan.proto.CadDetProto.CadDetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExecuteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_EXECUTE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXECUTE:
          serviceImpl.execute((com.chan.proto.CadDetProto.CadDetRequest) request,
              (io.grpc.stub.StreamObserver<com.chan.proto.CadDetProto.CadDetResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getExecuteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.chan.proto.CadDetProto.CadDetRequest,
              com.chan.proto.CadDetProto.CadDetResponse>(
                service, METHODID_EXECUTE)))
        .build();
  }

  private static abstract class CadDetServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CadDetServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.chan.proto.CadDetProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CadDetService");
    }
  }

  private static final class CadDetServiceFileDescriptorSupplier
      extends CadDetServiceBaseDescriptorSupplier {
    CadDetServiceFileDescriptorSupplier() {}
  }

  private static final class CadDetServiceMethodDescriptorSupplier
      extends CadDetServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CadDetServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CadDetServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CadDetServiceFileDescriptorSupplier())
              .addMethod(getExecuteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
