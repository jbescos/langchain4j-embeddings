package dev.langchain4j.model.embedding.onnx.bgesmallzhv15;

import dev.langchain4j.model.embedding.onnx.AbstractInProcessEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.OnnxBertBiEncoder;
import dev.langchain4j.model.embedding.onnx.PoolingMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static dev.langchain4j.internal.ValidationUtils.ensureNotNull;

/**
 * BAAI bge-small-zh-v1.5 embedding model that runs within your Java application's process.
 * <p>
 * Maximum length of text (in tokens) that can be embedded at once: unlimited.
 * However, while you can embed very long texts, the quality of the embedding degrades as the text lengthens.
 * It is recommended to embed segments of no more than 512 tokens long.
 * <p>
 * Embedding dimensions: 512
 * <p>
 * It is recommended to add "为这个句子生成表示以用于检索相关文章：" prefix to a query.
 * <p>
 * Uses an {@link Executor} to parallelize the embedding process.
 * By default, uses a cached thread pool with the number of threads equal to the number of available processors.
 * Threads are cached for 1 second.
 * <p>
 * More details <a href="https://huggingface.co/BAAI/bge-small-zh-v1.5">here</a>
 */
public class BgeSmallZhV15EmbeddingModel extends AbstractInProcessEmbeddingModel {

    private static final OnnxBertBiEncoder MODEL = loadFromJar(
            "bge-small-zh-v1.5.onnx",
            "bge-small-zh-v1.5-tokenizer.json",
            PoolingMode.CLS
    );

    /**
     * Creates an instance of an {@code BgeSmallZhV15EmbeddingModel}.
     * Uses a cached thread pool with the number of threads equal to the number of available processors.
     * Threads are cached for 1 second.
     */
    public BgeSmallZhV15EmbeddingModel() {
        super(null);
    }

    /**
     * Creates an instance of an {@code BgeSmallZhV15EmbeddingModel}.
     *
     * @param executor The executor to use to parallelize the embedding process.
     */
    public BgeSmallZhV15EmbeddingModel(Executor executor) {
        super(ensureNotNull(executor, "executor"));
    }

    @Override
    protected OnnxBertBiEncoder model() {
        return MODEL;
    }

    @Override
    protected Integer knownDimension() {
        return 512;
    }
}
